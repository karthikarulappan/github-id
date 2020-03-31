/**
 * 
 */
package com.ka.githubid.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.ka.githubid.config.AppProperties;
import com.ka.githubid.resource.User;

import io.swagger.annotations.ApiParam;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @author Karthik Arulappan
 *
 */
@RestController
@RequestMapping("api/v1")
public class GithubIdController {

	private AppProperties appProp;
	private WebClient webClient;

	@Autowired
	public GithubIdController(WebClient webClient, AppProperties appProp) {
		this.webClient = webClient;
		this.appProp = appProp;
	}

	@GetMapping("/follower/{uname}")
	public User retrieveDetails(
			@ApiParam(value = "Please enter a valid github user name") @PathVariable(name = "uname", required = true) String uname) {
		User user = User.builder().login(uname).followers(fetchFollowersforAnUser(uname)).build();
		return fetchDetails(user);
	}

	// Method to fetch the details of an User 3 level deep
	private User fetchDetails(User user) {
		if (user.getFollowers() != null && !user.getFollowers().isEmpty())
			user.setFollowers(
					Flux.fromIterable(user.getFollowers()).parallel().runOn(Schedulers.elastic()).doOnNext(i -> {
						i.setFollowers(fetchFollowersforAnUser(i.getLogin()));
						if (i.getFollowers() != null && !i.getFollowers().isEmpty()) {
							List<User> userList_1 = i.getFollowers();
							userList_1.forEach(ele_1 -> {
								ele_1.setFollowers(fetchFollowersforAnUser(ele_1.getLogin()));
							});
						}
					}).sequential().collectList().block());
		return user;
	}

	// Method to retrieve the followers (limit 5) for an given username
	private List<User> fetchFollowersforAnUser(String user) {
		return webClient.get().uri(appProp.getFollowersUrl(), user).exchange()
				.flatMapMany(clientResponse -> clientResponse.bodyToFlux(User.class)).toStream().limit(5)
				.collect(Collectors.toList());
	}
}

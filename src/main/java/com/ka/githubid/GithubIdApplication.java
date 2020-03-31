package com.ka.githubid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import com.ka.githubid.config.AppProperties;

/**
 * @author Karthik Arulappan
 */
@SpringBootApplication
public class GithubIdApplication {

	@Autowired
	private AppProperties appProp;

	public static void main(String[] args) {
		SpringApplication.run(GithubIdApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public WebClient builder() {
		return WebClient.builder().baseUrl(appProp.getBaseUrl())
				.defaultHeader(HttpHeaders.CONTENT_TYPE, appProp.getContentType())
				.defaultHeader(HttpHeaders.USER_AGENT, appProp.getUserAgent())
				.defaultHeader(HttpHeaders.AUTHORIZATION, appProp.getToken()).build();
	}

}

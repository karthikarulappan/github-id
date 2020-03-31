/**
 * 
 */
package com.ka.githubid.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Karthik Arulappan
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket githubIdApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("github-id", "A spring boot app which gives you the github user and follower details", "V1",
				"https://www.apache.org/licenses/LICENSE-2.0",
				new Contact("Karthik Arulappan", "https://github.com/karthikarulappan/github-id",
						"karthik201188@gmail.com"),
				"License", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}

}

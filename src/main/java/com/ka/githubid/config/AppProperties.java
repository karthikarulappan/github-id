/**
 * 
 */
package com.ka.githubid.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author Karthik Arulappan
 *
 */
@Data
@Component
@ConfigurationProperties(prefix = "githubid")
public class AppProperties {

	private String baseUrl;
	private String followersUrl;
	private String userUrl;
	private String token;
	private String userAgent;
	private String contentType;

}

package com.authorize.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AuthorizationS2MaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationS2MaApplication.class, args);
	}

}

package com.authorize.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuthorizationS2MaApplication {

	public static void main(String[] args) {        
		//Increase Timeout of Server so it will not give  504 error
		System.setProperty("server.connection-timeout","60000");
		SpringApplication.run(AuthorizationS2MaApplication.class, args);
	}

}

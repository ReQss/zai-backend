package com.triptip.triptip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ShoppingApp {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApp.class, args);

	}

}

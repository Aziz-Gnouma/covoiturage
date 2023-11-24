package com.example.covoiturage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class CovoiturageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovoiturageApplication.class, args);
	}

}

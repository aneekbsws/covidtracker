package com.springproject.covid.CovidProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CovidProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidProjectApplication.class, args);
	}

}

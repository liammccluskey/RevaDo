package com.example.RevaDo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RevaDoApplication {

	public static void main(String[] args) {
		System.out.println("starting");
		SpringApplication.run(RevaDoApplication.class, args);
		System.out.println("exiting");
	}
}

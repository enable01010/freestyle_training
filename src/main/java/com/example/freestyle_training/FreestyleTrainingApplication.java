package com.example.freestyle_training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FreestyleTrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreestyleTrainingApplication.class, args);
		System.out.println("起動完了しました");
		System.out.println("http://localhost:8080/DebugStart");

	}
}

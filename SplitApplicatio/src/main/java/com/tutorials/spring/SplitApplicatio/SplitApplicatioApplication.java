package com.tutorials.spring.SplitApplicatio;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SplitApplicatioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SplitApplicatioApplication.class, args);
	}

}


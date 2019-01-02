package com.tutorials.spring.FlowApplication.configurations;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowConfiguration {

	@Autowired public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.tasklet((StepContribution contribution, ChunkContext chunkContext)->{
					System.out.println("Step 1\tInside Initial Flow Configuration");
					return RepeatStatus.FINISHED;
				}).build();
	}
	
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
				.tasklet((StepContribution contribution, ChunkContext chunkContext)->{
					System.out.println("Step 2\tInside Initial Flow Configuration");
					return RepeatStatus.FINISHED;
				}).build();
	}
	
	@Bean
	public Flow foo() {
		FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("foo");
		
		flowBuilder.start(step1())
		.next(step2())
		.end();
		
		return flowBuilder.build();
	}
}

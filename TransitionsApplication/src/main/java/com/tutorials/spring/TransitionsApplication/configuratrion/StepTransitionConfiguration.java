package com.tutorials.spring.TransitionsApplication.configuratrion;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepTransitionConfiguration {

	@Autowired public JobBuilderFactory jobBuilderFactory;
	@Autowired public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.tasklet(new Tasklet() {
					
					@Override
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						System.out.println(">>This is step 1");
						return RepeatStatus.FINISHED;
					}
				}).build();
	}
	
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step1")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) ->{
					System.out.println(">>This is step 2");
					return RepeatStatus.FINISHED;
				}
						).build();
	}
	
	@Bean
	public Step step3() {
		return stepBuilderFactory.get("step1")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) ->{
					System.out.println(">>This is step 3");
					return RepeatStatus.FINISHED;
				}
						).build();
	}
	
//	@Bean
//	public Job transitionJobSimpleNext() {
//		return jobBuilderFactory.get("transtionJob")
//				.start(step1())
//				.next(step2())
//				.next(step3())
//				.next(step1())
//				.next(step3())
//				.build();
//	}
	
	
	@Bean
	public Job transitionJobUsingQueryNext() {
		return jobBuilderFactory.get("transtionQueryJob")
				.start(step1())
				.on("COMPLETED").to(step2())
				.from(step2()).on("COMPLETED").fail()
				.from(step3()).end()
				.build();
	}
}

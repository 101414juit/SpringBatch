package com.tutorials.spring.FlowApplication.configurations;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LastFlowConfiguration {

	@Autowired public StepBuilderFactory stepBuilderFactory;
	@Autowired public JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public Step lastStep() {
		return stepBuilderFactory.get("lastStep")
				.tasklet((StepContribution contribution, ChunkContext chunkContext)->{
					System.out.println("Inside last Flow Configuration");
					return RepeatStatus.FINISHED;
				}).build();
	}
	
	@Bean
	public Job lastFlowJob(Flow flow) {
		return jobBuilderFactory.get("LastConfiguration")
				.start(lastStep())
				.on("COMPLETED").to(flow)
				.end()
				.build();
	}
}

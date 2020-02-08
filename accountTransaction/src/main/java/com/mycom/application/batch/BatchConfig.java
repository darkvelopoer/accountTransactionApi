package com.mycom.application.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mycom.application.service.TransactionService;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private TransactionService transactionService;

	@Bean
	public ReadTask readTask(TransactionService transactionService) {
		return new ReadTask(transactionService);
	}

	@Bean
	public Step stepOne() {
		return stepBuilderFactory.
				get("stepOne").
				tasklet(new ReadTask(transactionService)).
				build();
	}

	@Bean
	public Job demoJob() {
		return jobBuilderFactory.
				get("demoJob").
				incrementer(new RunIdIncrementer()).
				start(stepOne()).build();
	}
}

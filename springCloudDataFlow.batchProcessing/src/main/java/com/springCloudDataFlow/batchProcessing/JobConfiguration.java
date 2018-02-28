package com.springCloudDataFlow.batchProcessing;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.batch.core.Job;
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
public class JobConfiguration {
 
    private static Log logger
      = LogFactory.getLog(JobConfiguration.class);
 
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
 
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
 
    @Bean
    public Job printJob() {
        return jobBuilderFactory.get("printJob")
          .start(stepBuilderFactory.get("printJobStep1")
          .tasklet(new Tasklet() {
             
              @Override
              public RepeatStatus execute(StepContribution contribution, 
                ChunkContext chunkContext) throws Exception {
                 
                logger.info("printJob is running ..............");
                return RepeatStatus.FINISHED;
              }
        }).build()).build();
    }
    
    @Bean
    public Job scanJob() {
        return jobBuilderFactory.get("scanJob")
          .start(stepBuilderFactory.get("scanJobStep1")
          .tasklet(new Tasklet() {
             
              @Override
              public RepeatStatus execute(StepContribution contribution, 
                ChunkContext chunkContext) throws Exception {
                 
                logger.info("scanJob is running ..............");
                return RepeatStatus.FINISHED;
              }
        }).build()).build();
    }
    
}
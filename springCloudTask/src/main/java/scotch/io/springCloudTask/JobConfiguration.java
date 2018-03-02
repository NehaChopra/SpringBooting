package scotch.io.springCloudTask;

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

    private static final Log logger = LogFactory.getLog(JobConfiguration.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job1() {
        return jobBuilderFactory.get("job51").start(stepBuilderFactory.get("job51Step1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                logger.info("Job51 was run");
                return RepeatStatus.FINISHED;
            }
        }).build()).build();
    }
    
    @Bean
    public Job job2() {
        return jobBuilderFactory.get("job61").start(stepBuilderFactory.get("job61Step1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                logger.info("Job61 was run");
                return RepeatStatus.FINISHED;
            }
        }).build()).build();
    }
    
    @Bean
    public Job job3() {
        return jobBuilderFactory.get("job11").start(stepBuilderFactory.get("job11Step1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                logger.info("Job11 was run");
                return RepeatStatus.FINISHED;
            }
        }).build()).build();
    }
    
    @Bean
    public Job job4() {
        return jobBuilderFactory.get("job81").start(stepBuilderFactory.get("job81Step1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                logger.info("Job81 was run");
                return RepeatStatus.FINISHED;
            }
        }).build()).build();
    }
}
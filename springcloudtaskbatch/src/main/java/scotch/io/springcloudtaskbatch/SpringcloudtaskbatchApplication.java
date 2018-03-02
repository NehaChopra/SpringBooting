package scotch.io.springcloudtaskbatch;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableTask
@EnableBatchProcessing
public class SpringcloudtaskbatchApplication {

    private final static Logger LOGGER = Logger.getLogger(SpringcloudtaskbatchApplication.class.getName());
    
    @Autowired
    private DataSource dataSource;

    @Bean
    public CustomizedTaskConfigure getTaskConfigurer()
    {
        return new CustomizedTaskConfigure(dataSource);
    }
    
    @Bean
    public TaskListener taskListener() {
        return new TaskListener();
    }
    
	public static void main(String[] args) {
		SpringApplication.run(SpringcloudtaskbatchApplication.class, args);
	}
	
    @Component
    public static class SpringcloudtaskbatchApplicationRunner implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments arg0) throws Exception {
            // TODO Auto-generated method stub
            LOGGER.info("Inside customized ApplicationRunner \"SpringcloudtaskbatchApplicationRunner\" of spring cloud batch application..... ");
        }
    }
}

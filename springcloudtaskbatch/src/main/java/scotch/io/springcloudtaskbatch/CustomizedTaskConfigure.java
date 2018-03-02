package scotch.io.springcloudtaskbatch;

import javax.sql.DataSource;
import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;

public class CustomizedTaskConfigure extends DefaultTaskConfigurer {

    public CustomizedTaskConfigure(DataSource dataSource) {
        super(dataSource);
    }
}

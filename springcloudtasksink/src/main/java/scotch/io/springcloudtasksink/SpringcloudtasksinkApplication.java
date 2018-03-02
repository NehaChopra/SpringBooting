package scotch.io.springcloudtasksink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.launcher.annotation.EnableTaskLauncher;


@SpringBootApplication
@EnableTaskLauncher
public class SpringcloudtasksinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudtasksinkApplication.class, args);
	}
}

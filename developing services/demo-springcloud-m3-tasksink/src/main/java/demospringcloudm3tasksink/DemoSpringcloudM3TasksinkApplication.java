package demospringcloudm3tasksink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.launcher.annotation.EnableTaskLauncher;

@SpringBootApplication
@EnableTaskLauncher
public class DemoSpringcloudM3TasksinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringcloudM3TasksinkApplication.class, args);
	}

}

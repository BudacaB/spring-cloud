package fast.pass.console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FastPassConsoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastPassConsoleApplication.class, args);
	}

}

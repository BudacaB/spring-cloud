package demo.tollratesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TollratesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TollratesServiceApplication.class, args);
	}

}

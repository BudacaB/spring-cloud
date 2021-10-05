package demo.fastpasscustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FastPassCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastPassCustomerApplication.class, args);
	}

}

package fast.pass.console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@LoadBalancerClient(name="fastpass-service", configuration=LoadBalancerConfig.class)
@SpringBootApplication
public class FastPassConsoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastPassConsoleApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}
}

package demo.springcloud.m5.zipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZipkinServer
public class DemoSpringcloudM5ZipkinserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringcloudM5ZipkinserverApplication.class, args);
	}

}

package com.spring.demospringcloudm2configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class DemoSpringcloudM2ConfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringcloudM2ConfigserverApplication.class, args);
	}

}

package com.demo.springcloud.demospringcloudm2configservergit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class DemoSpringcloudM2ConfigserverGitApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringcloudM2ConfigserverGitApplication.class, args);
	}

}

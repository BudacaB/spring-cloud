package com.demo.springcloud.m3.task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableTask
public class DemoSpringcloudM3TaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringcloudM3TaskApplication.class, args);
	}

	@Bean
	public TollProcessingTask tollProcessingTask() {
		return new TollProcessingTask();
	}

	public class TollProcessingTask implements CommandLineRunner {

		@Override
		public void run(String... strings) {

			// parameters stationid, license plate, timestamp
			if (null != strings) {
				System.out.println("parameter length is " + strings.length);

				String stationId = strings[0];
				String licensePlate = strings[1];
				String timestamp = strings[2];

				System.out.println("Station ID is " + stationId + ", plate is " + licensePlate + ", timestamp is " + timestamp);
			}
		}
	}
}

package com.example.VulServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableMongoRepositories
@SpringBootApplication
public class VulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VulServerApplication.class, args);
	}

}

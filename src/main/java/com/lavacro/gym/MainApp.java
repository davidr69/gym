package com.lavacro.gym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class MainApp {
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MainApp.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}
}

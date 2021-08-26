package com.ieti.ieti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ieti.ieti"})
public class IetiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IetiApplication.class, args);
	}

}

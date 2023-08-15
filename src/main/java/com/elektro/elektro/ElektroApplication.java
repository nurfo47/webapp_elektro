package com.elektro.elektro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"com.elektro.elektro"})
public class ElektroApplication {


	public static void main(String[] args) {
		SpringApplication.run(ElektroApplication.class, args);
	}

}

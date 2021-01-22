package com.angularAirport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
@ServletComponentScan
@SpringBootApplication
public class AngularAirportSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngularAirportSpringbootApplication.class, args);
	}

}

package com.beyondsoft.itsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.beyondsoft.itsm.core.annotation.ItsmSpringBootApplication;


@ItsmSpringBootApplication
public class ItsmApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(ItsmApplication.class, args);
	}
}

package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import config.DemoConfig;


public class Is296DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Is296DemoApplication.class, args);
		ApplicationContext context=new AnnotationConfigApplicationContext(DemoConfig.class);
		JdbcTemplate dao =context.getBean(JdbcTemplate.class);
		System.out.println(dao.queryForMap("SELECT * FROM POTENTIAL_LEADS LIMIT 1"));
	}

}

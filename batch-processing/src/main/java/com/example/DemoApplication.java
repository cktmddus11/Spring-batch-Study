package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.ctr.BatchController;
import com.example.demo.ctr.LcmgrTestScheduler;

@SpringBootApplication 
public class DemoApplication {
	
/*	@Autowired
	private BatchController controller;
	*/
	public static void main(String[] args){	
		SpringApplication.run(DemoApplication.class, args);
		//SpringApplication.run(LcmgrTestScheduler.class);
		//SpringApplication.run(DemoApplication.class, args);
	}
}

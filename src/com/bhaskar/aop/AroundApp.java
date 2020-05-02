package com.bhaskar.aop;


import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bhaskar.aop.dao.AopConfig;
import com.bhaskar.aop.service.TrafficFortuneService;

public class AroundApp {
	
	private static Logger myLogger=
			Logger.getLogger(AroundApp.class.getName());

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(AopConfig.class);
		
		// get the bean from spring container
		TrafficFortuneService theFortuneService = 
				context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		myLogger.info("\nMain Program: AroundDemoApp");
		
		myLogger.info("Calling getFortune");
		
		
		boolean tripWire=true;
		String data = theFortuneService.getFortune(tripWire);
		
		myLogger.info("\nMy fortune is: " + data);
		
		myLogger.info("Finished");
		
		// close the context
		context.close();
	}

}











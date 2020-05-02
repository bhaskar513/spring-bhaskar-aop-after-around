package com.bhaskar.aop;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bhaskar.aop.dao.AccountDAO;
import com.bhaskar.aop.dao.AopConfig;
import com.bhaskar.aop.dao.MembershipDAO;

public class AfterThrowingApp {

	public static void main(String[] args) {
		
		//read spring config java class 
		AnnotationConfigApplicationContext context=
				new AnnotationConfigApplicationContext(AopConfig.class);
		
		//get the bean from container
		AccountDAO theAccoutDAO=context.getBean("accountDAO", AccountDAO.class);
		
		//call the methods
		List<Account> theAccounts=null;
		
		try{
			 boolean tripWire=true;
			theAccounts=theAccoutDAO.findAccounts(tripWire);
		}
		catch(Exception exc)
		{
			System.out.println("\n\nMain Program ..... caught exception: "+ exc);
		}
		System.out.println("\n\n Main program: AfterThrowingApp ");
		System.out.println("----------------------");
		System.out.println(theAccounts);
		System.out.println("\n");
       //close the context
       context.close();
	}

}

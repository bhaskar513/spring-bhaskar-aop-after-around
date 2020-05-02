package com.bhaskar.aop.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.weaver.tools.cache.GeneratedCachedClassHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bhaskar.aop.Account;

@Aspect
@Component

@Order(2)
public class MyLoggingAspect {
	
	private  Logger myLogger=
			Logger.getLogger(getClass().getName());
	
@Around("execution(* com.bhaskar.aop.service.*.getFortune(..))")
public Object aroundGetFortune(
		ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{
	
	String method=theProceedingJoinPoint.getSignature().toShortString();
	myLogger.info("\n ========> Executing @Around  on method: "+ method);
	
	long begin =System.currentTimeMillis();
	
	Object result=null;
	
	try {
		result=theProceedingJoinPoint.proceed();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		myLogger.warning(e.getMessage());
		result="Major accident But no worries"; //comment this line and uncomment below line ==== run === code
		//throw e;   
	}
	
	long end=System.currentTimeMillis();
	
	long duration = end-begin;
	myLogger.info("\n ============= :" + duration / 1000.0 + "seconds");
	
	return result;
}
	
	
	
	@After("execution(* com.bhaskar.aop.dao.AccountDAO.findAccounts(..))")
	public void AfterFinallyFingAccountsAdvice(JoinPoint theJointPoint) {
		
		String method=theJointPoint.getSignature().toShortString();
		myLogger.info("\n ========> Executing @After (finally&&&&&)  on method: "+ method);
		
		
	}
	
	
	
	@AfterThrowing(
			pointcut="execution(* com.bhaskar.aop.dao.AccountDAO.findAccounts(..))",
			throwing="theExc")
	public void AfterThrowingFingAccountsAdvice(
			JoinPoint theJointPoint, Throwable theExc) {
		
		String method=theJointPoint.getSignature().toShortString();
		myLogger.info("\n ========> Executing @AfterThrowing on method: "+ method);
		myLogger.info("\n ======= theExc is : " +theExc);
		
	}
	
	// add a new advice for @AfterReturning
	@AfterReturning(
			pointcut="execution(* com.bhaskar.aop.dao.AccountDAO.findAccounts(..))",
			returning="result")
	public void afterReturningFindAccountAdvice(
			JoinPoint theJoinPoint, List<Account> result) {
		
		String method=theJoinPoint.getSignature().toShortString();
		myLogger.info("\n ========> Executing @AfterReturning on method: "+ method);
		myLogger.info("\n ======= result is : " +result);
		
		// let post process the data lets modify
		convertAccountNamesToUpperCase(result);
		myLogger.info("\n ======= result is : " +result);
	}
	
	/**
	 * @param result
	 */
	private void convertAccountNamesToUpperCase(List<Account> result) {
		for (Account tempAccount : result) {
			String theUpperName=tempAccount.getName().toUpperCase();
			tempAccount.setName(theUpperName);
		}
		
	}

	@Before("com.bhaskar.aop.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		myLogger.info("\n ===> executing MyLoggingAspect");
		
		// display the method signature
		MethodSignature methoSig=(MethodSignature) theJoinPoint.getSignature();
		myLogger.info("Method: " + methoSig);
		
		// display method arguments
		
		// Objects[] args
		
		Object[] args=theJoinPoint.getArgs();
		
		for(Object tempArg: args) {
			myLogger.info(tempArg.toString());
			
			if(tempArg instanceof Account) {
				Account theAccount=(Account) tempArg;
				myLogger.info("account name: " + theAccount.getName());
				myLogger.info("account lavel: " + theAccount.getLavel());
			}
			
		}
	}
	
	
	
	
}

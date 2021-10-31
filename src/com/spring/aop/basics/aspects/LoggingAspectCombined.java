package com.spring.aop.basics.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.spring.aop.basics.beans.Account;

@Aspect
@Component
@Order(2)
public class LoggingAspectCombined {

	@Autowired
	private Account dummyAccount;
	
	// Example of combining PointCut declarations
	@Before("com.spring.aop.basics.aspects.AOPExpressions.forDAOPackages() || com.spring.aop.basics.aspects.AOPExpressions.forGetters()")
	public void beforeAdviceGetterAndDAO(JoinPoint joinPoint) {
		String methodSignature = joinPoint.getSignature().toShortString();
		System.out
		.println("From before advice combining pointcut declarations for method signature: " + methodSignature);
		Object[] methodArgs = joinPoint.getArgs();
		System.out.println("Following are the method args: " + Arrays.toString(methodArgs));
	}

	// Example of AfterRetruning advice
	// Return type has to match then only advice will execute
	// Advice will run after method execution is completed(successful execution)
	// We can also have pointcut="execution(* com.real.vivek.dao.*.get*(..))" in
	// @AfterReturning instead of value
	@AfterReturning(value = "com.spring.aop.basics.aspects.AOPExpressions.forDAOPackages() || com.spring.aop.basics.aspects.AOPExpressions.forGetters()", returning = "account")
	public void afterAdviceGetterAndDAO(JoinPoint joinPoint, Account account) {
		System.out.println("From after retruning advice");
		System.out.println("The return value is " + account);
	}

	// Example of after throwing advice
	// AfterThrowing doesn't stop propagation of exception it only intercepts
	// it(reads it)
	@AfterThrowing(value = "com.spring.aop.basics.aspects.AOPExpressions.forDAOPackages() || com.spring.aop.basics.aspects.AOPExpressions.forGetters()", throwing = "theexc")
	public void afterThrwoingGetterAndDAO(JoinPoint joinPoint, Exception theexc) {
		System.out.println("From after throwing advice");
		System.out.println("The expection thrown is from the method: " + joinPoint.getSignature().toShortString()
				+ " the exception thrown is:" + theexc);
	}

	// Example of after advice. It acts like finally block it runs irrespective of
	// success or failure of method
	// After advice is called after the after throwing advice/after returning advice
	// from spring 5.2.7 and later
	// It doesn't have access to exception. Our code should be independent of happy
	// path or exception
	@After(value = "com.spring.aop.basics.aspects.AOPExpressions.forDAOPackages() || com.spring.aop.basics.aspects.AOPExpressions.forGetters()")
	public void afterThrwoingGetterAndDAO(JoinPoint joinPoint) {
		System.out.println("From after advice");
		System.out.println("The after advice is from the method: " + joinPoint.getSignature().toShortString());
	}

	// Example of Around advice
	// Executes before and after the method
	// Executes after the after advice and before the before advice
	// We have to return the retrieved object from proceed
	@Around(value = "com.spring.aop.basics.aspects.AOPExpressions.forDAOPackages()")
	public Object afterThrwoingGetterAndDAO(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("From around advice");

		Object result;
		long methodStartTime = System.currentTimeMillis();

		try {
			// proceed starts the execution of method
			result = proceedingJoinPoint.proceed();
			System.out.println("Got the following after method execution: " + result);
		} catch (Throwable throwable) {
			// Exception propagation can be stopped from here . Also new exception can be
			// thrown from here
			// However the advices that we have written for the code after this don't work if we create the object using new keyword
			System.out.println("@Around advice caught exception: " + throwable);
			// We cannot use the code below because we will be creating object and not spring so it will not follow aspects
			// Account dummyAccount = new Account();
			dummyAccount.setName("H.R. Account");
			dummyAccount.setDescription("H.R. Account created from Around advice");
			result = dummyAccount;
		}
		long methodEndTime = System.currentTimeMillis();
		System.out.println("The method execution took: " + (methodEndTime - methodStartTime) + " milliseconds");
		System.out.println("Returning following from around advice: " + result);
		return result;
	}
}

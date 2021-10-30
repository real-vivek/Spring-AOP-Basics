package com.spring.aop.basics.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	// Example of @Before advice
	// execution(modifier-pattern? Return-type declaring-type-pattern?
	// Method-name-pattern(param-pattern) throws-pattern?)
	// Pattern is optional if it has ?
	// For param-pattern we can give:
	// (): method with no args
	// (*): 1 arg of any type
	// (..): 0 or any number of args of any type
	// To match specific class method we need to 852give fully qualified name
	@Before("forDAOPackages()")
	public void beforeAdviceAccountDAO() {
		System.out.println("From before advice pointcut declaration");
	}

	// Example of PointCut declaration for addAccount of AccountDAO class
	@Pointcut("execution(* com.spring.aop.basics.dao.AccountDAO.addAccount())")
	private void forDAOPackages() {
	}

	// Example of PointCut declaration for any getter
	@Pointcut("execution(* get*(..))")
	private void forGetters() {
	}

	@Before("forGetters()")
	public void beforeAdviceGetter() {
		System.out.println("From before advice getter method");
	}

}

package com.spring.aop.basics.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class LoggingAspectDAO {

	// Example of @Before advice
	// execution(modifier-pattern? Return-type declaring-type-pattern?
	// Method-name-pattern(param-pattern) throws-pattern?)
	// Pattern is optional if it has ?
	// For param-pattern we can give:
	// (): method with no args
	// (*): 1 arg of any type
	// (..): 0 or any number of args of any type
	// To match specific class method we need to 852give fully qualified name
	@Before("com.spring.aop.basics.aspects.AOPExpressions.forDAOPackages()")
	public void beforeAdviceAccountDAO() {
		System.out.println("From before advice pointcut declaration");
	}
}

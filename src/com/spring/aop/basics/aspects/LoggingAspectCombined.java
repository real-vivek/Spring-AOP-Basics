package com.spring.aop.basics.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class LoggingAspectCombined {

	// Example of combining PointCut declarations
	@Before("com.spring.aop.basics.aspects.AOPExpressions.forDAOPackages() || com.spring.aop.basics.aspects.AOPExpressions.forGetters()")
	public void beforeAdviceGetter() {
		System.out.println("From before advice combining pointcut declarations");
	}
}

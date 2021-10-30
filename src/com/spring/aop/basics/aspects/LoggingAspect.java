package com.spring.aop.basics.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	@Before("execution(public void addAccount())")
	public void beforeAdviceAccountDAO() {
		System.out.println("From before advice");
	}
}

package com.spring.aop.basics.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.spring.aop.basics.beans.Account;

@Aspect
@Component
@Order(2)
public class LoggingAspectCombined {

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

	@AfterThrowing(value = "com.spring.aop.basics.aspects.AOPExpressions.forDAOPackages() || com.spring.aop.basics.aspects.AOPExpressions.forGetters()", throwing = "theexc")
	public void afterThrwoingGetterAndDAO(JoinPoint joinPoint, Exception theexc) {
		System.out.println("From after throwing advice");
		System.out.println("The expection thrown is from the method: " + joinPoint.getSignature().toShortString()
				+ " the exception thrown is:" + theexc);
	}
}

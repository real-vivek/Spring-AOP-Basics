package com.spring.aop.basics.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AOPExpressions {

	@Pointcut("execution(* com.spring.aop.basics.dao.AccountDAO.addAccount())")
	public void forDAOPackages() {
	}

	// Example of PointCut declaration for any getter
	@Pointcut("execution(* get*(..))")
	public void forGetters() {
	}
}

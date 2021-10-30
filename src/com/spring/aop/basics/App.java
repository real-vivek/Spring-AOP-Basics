package com.spring.aop.basics;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.aop.basics.beans.Account;
import com.spring.aop.basics.dao.AccountDAO;

public class App {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(
				Config.class);
		AccountDAO acccountBean = (AccountDAO) annotationConfigApplicationContext.getBean("accountDAO");
		Account account = acccountBean.addAccount();
		account.getDescription();
		account.getName();
		annotationConfigApplicationContext.close();
	}

}

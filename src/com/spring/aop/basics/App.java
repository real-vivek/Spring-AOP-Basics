package com.spring.aop.basics;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.aop.basics.beans.Account;
import com.spring.aop.basics.dao.AccountDAO;

public class App {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(
				Config.class);
		AccountDAO acccountBean = (AccountDAO) annotationConfigApplicationContext.getBean("accountDAO");

		// Note that we have to register account class with spring and then only spring
		// will inject advice to give methods(getter/setter) of bean
		Account account = (Account) annotationConfigApplicationContext.getBean("account");
		// Below code will not work as account pojo is not registered with spring
		// Account account = new Account();

		account.setName("I.T. Account");
		account.setDescription("I.T. Account manages for I.T. resources");

		Account retrievedAccount = acccountBean.addAccount(account);
		retrievedAccount.getDescription();
		retrievedAccount.getName();
		annotationConfigApplicationContext.close();
	}

}

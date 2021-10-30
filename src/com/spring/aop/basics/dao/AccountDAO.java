package com.spring.aop.basics.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.aop.basics.beans.Account;

@Component
public class AccountDAO {

	// Note that we have to register account class with spring and then only spring
	// will inject advice to give methods(getter/setter) of bean
	@Autowired
	Account account;

	public Account addAccount() {
		// Below code will not work as account pojo is not registered with spring
		// Account account = new Account();
		account.setName("I.T. Account");
		account.setDescription("I.T. Account manages for I.T. resources");
		System.out.println(getClass() + ": Doing account DAO work");
		return account;
	}

}

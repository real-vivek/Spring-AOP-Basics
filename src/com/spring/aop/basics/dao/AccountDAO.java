package com.spring.aop.basics.dao;

import org.springframework.stereotype.Component;

import com.spring.aop.basics.beans.Account;

@Component
public class AccountDAO {

	public Account addAccount(Account account,boolean throwExe) {
		if(throwExe) {
			System.out.println("Throwing the exception!!");
			throw new RuntimeException();
		}
		System.out.println(getClass() + ": Doing account DAO work");
		return account;
	}

}
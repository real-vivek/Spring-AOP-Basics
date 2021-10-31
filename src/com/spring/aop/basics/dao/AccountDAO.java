package com.spring.aop.basics.dao;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.spring.aop.basics.beans.Account;

@Component
public class AccountDAO {

	public Account addAccount(Account account,boolean throwExe) throws InterruptedException {
		if(throwExe) {
			System.out.println("Throwing the exception!!");
			throw new RuntimeException();
		}
		TimeUnit.SECONDS.sleep(5);
		System.out.println(getClass() + ": Doing account DAO work");
		return account;
	}

}
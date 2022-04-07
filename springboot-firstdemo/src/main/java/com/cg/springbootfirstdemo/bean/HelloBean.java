package com.cg.springbootfirstdemo.bean;

import org.springframework.stereotype.Component;

@Component("hello")
public class HelloBean {
	
	public void sayHello()
	{
		System.out.println("Hello, Welcome to SpringBoot Demo-1");
	}
}

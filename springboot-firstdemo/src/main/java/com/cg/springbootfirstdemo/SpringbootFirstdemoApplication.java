package com.cg.springbootfirstdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cg.springbootfirstdemo.bean.HelloBean;

//@SpringBootApplication
@SpringBootApplication(proxyBeanMethods = false)
public class SpringbootFirstdemoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SpringbootFirstdemoApplication.class, args);
		ApplicationContext ctx = SpringApplication.run(SpringbootFirstdemoApplication.class, args);
		HelloBean hb = (HelloBean)ctx.getBean("hello");
		hb.sayHello();
	}
}

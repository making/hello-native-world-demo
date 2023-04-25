package com.example;

public class DefaultHelloService implements HelloService {
	@Override
	public String hello() {
		return "Hello World!";
	}
}

package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping(path = "/")
	public String hello() {
		return new DefaultHelloService().hello();
	}

	@GetMapping(path = "/", params = "type")
	public String helloByType(@RequestParam String type) throws Exception {
		Class<?> clazz = Class.forName(type);
		HelloService helloService = (HelloService) clazz
				.getDeclaredConstructor()
				.newInstance();
		return helloService.hello();
	}
}

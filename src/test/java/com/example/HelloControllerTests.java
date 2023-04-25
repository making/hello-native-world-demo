package com.example;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HelloController.class)
@Import(NativeConfig.class)
class HelloControllerTests {
	@Autowired
	MockMvc mockMvc;

	@Test
	void hello() throws Exception {
		this.mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(content().string("Hello World!"));
	}

	@Test
	void helloByTypeDefault() throws Exception {
		this.mockMvc.perform(get("/").param("type", "com.example.DefaultHelloService"))
				.andExpect(status().isOk())
				.andExpect(content().string("Hello World!"));
	}

	@Test
	void helloByTypeText() throws Exception {
		this.mockMvc.perform(get("/").param("type", "com.example.TextHelloService"))
				.andExpect(status().isOk())
				.andExpect(content().string("Hello from text!"));
	}
}

package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

public class TextHelloService implements HelloService {
	@Override
	public String hello() {
		try (final InputStream in = new ClassPathResource("hello.txt").getInputStream()) {
			return StreamUtils.copyToString(in, StandardCharsets.UTF_8);
		}
		catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}

package com.example;

import com.example.NativeConfig.NativeHints;

import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

@Configuration(proxyBeanMethods = false)
@ImportRuntimeHints(NativeHints.class)
public class NativeConfig {
	public static class NativeHints implements RuntimeHintsRegistrar {
		@Override
		public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
			try {
				hints.reflection()
						.registerConstructor(DefaultHelloService.class.getDeclaredConstructor(), ExecutableMode.INVOKE)
						.registerConstructor(TextHelloService.class.getDeclaredConstructor(), ExecutableMode.INVOKE);
				hints.resources()
						.registerPattern("*.txt");
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}

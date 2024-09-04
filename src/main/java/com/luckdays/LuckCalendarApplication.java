package com.luckdays;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

@SpringBootApplication
public class LuckCalendarApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LuckCalendarApplication.class, args);
	}


	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		String port = System.getenv("PORT");
		if (port != null) {
			tomcat.setPort(Integer.parseInt(port));
		}
		return tomcat;
	}
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
}

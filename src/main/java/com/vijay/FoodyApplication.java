package com.vijay;

import com.vijay.filters.AuthFilter;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FoodyApplication {

	public static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FoodyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FoodyApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public FilterRegistrationBean jwtInterceptor() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new AuthFilter());
		registrationBean.setOrder(1);
		registrationBean.addUrlPatterns("/api/v1/*");
		return registrationBean;
	}
}

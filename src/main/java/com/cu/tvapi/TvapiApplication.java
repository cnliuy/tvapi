package com.cu.tvapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.cu.tvapi.filter.AccessFilter;

@SpringBootApplication
@EnableZuulProxy
public class TvapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TvapiApplication.class, args);
	}
	
	/**
	 * 增加过滤器
	 * */
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
}

package com.starter.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Profile;

@SpringBootApplication
@MapperScan("com.starter.demo.mapper")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
//
//	@Bean
////	@Profile({"dev","test"})// 设置 dev test 环境开启
//	public PerformanceInterceptor performanceInterceptor() {
//		return new PerformanceInterceptor();
//	}

}

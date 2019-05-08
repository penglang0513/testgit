package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.dao")
@ServletComponentScan
@EnableCaching
public class PenglangApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(PenglangApplication.class, args);
    }
}

package com.qfang.examples.spring.cloud.web.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: liaozhicheng
 * @date: 2019-07-20
 * @since 1.0
 */
@SpringBootApplication
@ImportAutoConfiguration
public class WebDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebDemoApplication.class, args);
    }

}

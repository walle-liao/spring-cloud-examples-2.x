package com.qfang.examples.spring.cloud.web.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author: liaozhicheng
 * @date: 2019-07-06
 * @since 1.0
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

//    @Override
//    protected void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/cors/**")
//                .allowedOrigins("http://127.0.0.1:8666")  // 注这里使用 localhost:8666 和 127.0.0.1:8666 有区别，跟客户端访问 url 地址保持一致即可
//                .allowCredentials(true)
//                .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
//                .maxAge(3600);
//    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/*")
                .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

}

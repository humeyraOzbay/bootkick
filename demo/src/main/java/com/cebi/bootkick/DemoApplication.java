package com.cebi.bootkick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
// same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableCaching
@EnableAsync
@EnableScheduling
public class DemoApplication extends SpringBootServletInitializer implements
        WebApplicationInitializer {
	// WebApplicationInitializer is for weblogic

    // Used when deploying to a standalone servlet container, i.e. tomcat
    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(DemoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // generic cache bu
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("random", "another");
    }

    // netbeans
}

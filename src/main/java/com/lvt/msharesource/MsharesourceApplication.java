package com.lvt.msharesource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.lvt.msharesource", "com.lvt.msharesource.controller"})
public class MsharesourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsharesourceApplication.class, args);
    }

}

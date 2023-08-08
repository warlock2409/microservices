package com.spring_boot_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class EmailServer {
    public static void main(String[] args) {
        SpringApplication.run(EmailServer.class,args);
    }


}
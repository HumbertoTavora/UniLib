package com.example.bookService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class bookServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(bookServiceApplication.class, args);
    }
}

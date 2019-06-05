package com.alex.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAsync
@SpringBootApplication
@EnableWebMvc
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }
}

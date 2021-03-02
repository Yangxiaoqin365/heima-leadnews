package com.heima.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ArticleJarApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleJarApplication.class, args);
        System.out.println("启动成功了............");
    }
}

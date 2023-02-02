package com.lan;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.lan.mybatis.mapper")
public class StudentApplication{
    public static void main(String[] args) {


        SpringApplication.run(StudentApplication.class);
    }
}

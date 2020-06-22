package org.javaboy.vhr;

import org.javaboy.vhr.config.annotation.EnableAuthUserInjection;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.javaboy.vhr.mapper")
@EnableAuthUserInjection
public class Vhr2Application {

    public static void main(String[] args) {
        SpringApplication.run(Vhr2Application.class, args);
    }

}

package net.wulusai.javastudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"net.wulusai.javastudy.mapper"})
public class JavastudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavastudyApplication.class, args);
    }

}

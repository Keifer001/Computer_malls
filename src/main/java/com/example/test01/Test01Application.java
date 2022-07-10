package com.example.test01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import javax.servlet.annotation.MultipartConfig;

@Configuration //表示配置类
@SpringBootApplication
//告诉springboot当前项目的mapper接口位置
@MapperScan("com.example.test01.mapper")
public class Test01Application {
    public static void main(String[] args) {
        SpringApplication.run(Test01Application.class, args);
    }
    public MultipartConfigElement getMultipartConfigElement(){
        //创建一个配置的工厂类对象
        MultipartConfigFactory factory=new MultipartConfigFactory();
        //设置需要创建对象的相关信息
        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
        factory.setMaxRequestSize(DataSize.of(15,DataUnit.MEGABYTES));
        //通过工厂类来创建MultipartConfigElement对象
        return factory.createMultipartConfig();
    }

}

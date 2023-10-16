package com.example.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

@Configuration      // 这是一个配置注解，说明这个类中有一些配置代码
@SpringBootApplication  // 将这个类标注成一个SpringBoot项目启动类
@MapperScan("com.example.shop.mapper")      // Mybatis管辖范围
public class ShopApplication implements WebMvcConfigurer {

    public static void main(String[] args) {        // 主函数
        SpringApplication.run(ShopApplication.class, args);     // 整个项目的入口
        // 项目成功启动标志
        System.out.println("(♥◠‿◠)ﾉﾞ  后台启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }





    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {     // 虚拟路径：在上传图片成功后要用，可以将未显示出来的图片显示出来
        registry.addResourceHandler("/shop/**").addResourceLocations("file:D:/create/shop/");
    }

}

package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author ljh
 * @version 1.0
 * @date 2020/9/20 11:10
 * @description 标题
 * @package com.changgou
 */
@SpringBootApplication
@EnableEurekaClient//开启eureka的客户端
//扫描指定的包下的所有的接口 为接口产生代理对象交给spring容器 mapper扫描
//@MapperScan(basePackages = "com.changgou.goods.dao")

//通用的mapper提供的mapper扫描注解
@MapperScan(basePackages = "com.changgou.goods.dao")
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}

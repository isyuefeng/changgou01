package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author yf
 * @date 2021/5/25
 **/
//(exclude = DataSourceAutoConfiguration.class)  排除掉 数据源的配置类 表示不配置数据源
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient//启用eureka的客户端
public class FileApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }
}
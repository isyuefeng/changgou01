package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ljh
 * @version 1.0
 * @date 2020/9/20 10:57
 * @description 标题
 * @package com.changgou
 */
@SpringBootApplication
@EnableEurekaServer//开启eureka的服务端
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}

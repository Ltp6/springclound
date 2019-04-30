package com.ltp.springcloud;

import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableEurekaClient
//使用随机负载均衡
@RibbonClient(name = "MICROSERVICECLOUD-DEPT",configuration = RandomRule.class)
@EnableFeignClients(basePackages = {"com.ltp.springcloud"})
@Component("com.ltp.springcloud")
public class DeptConsumer80_Feign_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_Feign_App.class, args);
    }
}

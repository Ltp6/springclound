package com.ltp.myrule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
* 配置自定义负载均衡算法
*
* */
@Configuration
public class MyIrule {
    @Bean
    public IRule myRule(){
        return new MySelfRule();//使用自定义负载均衡算法
    }
}

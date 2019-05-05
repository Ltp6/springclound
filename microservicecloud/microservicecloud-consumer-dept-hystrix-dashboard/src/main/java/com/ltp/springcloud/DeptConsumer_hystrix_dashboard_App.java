package com.ltp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class DeptConsumer_hystrix_dashboard_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_hystrix_dashboard_App.class, args);
    }
}

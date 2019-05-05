package com.ltp.springcloud.controller;


import com.ltp.springcloud.entities.Dept;
import com.ltp.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    //服务发现
    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/dept/add")
    public Boolean add(@RequestBody Dept dept) {
        return deptService.add(dept);
    }

    @GetMapping(value = "/dept/get/{id}")
    //一旦调用服务方法失败并抛出错误信息后，会自动调用HystrixCommand注解中fallbackMethod标注的方法。
    @HystrixCommand(fallbackMethod = "processHystrix_GET")
    public Dept get(@PathVariable(value = "id") Long id) {
        Dept dept = deptService.get(id);
        if (null == dept) {
            throw new RuntimeException("该ID：" + id + "没有对应的信息");
        }

        return dept;
    }

    public Dept processHystrix_GET(@PathVariable(value = "id") Long id) {

        return new Dept().setDeptno(id).setDname("该ID" + id + "没有对应的信息null--@HystrixXCommand")
                .setDb_source("No this database in Mysql");
    }


    @GetMapping(value = "/dept/list")
    public List<Dept> findAll() {
        return deptService.list();
    }

    //向外暴露服务发现接口
    @GetMapping(value = "/dept/discovery")
    public Object discovery() {
        //查询eureka的微服务有哪些
        List<String> list = discoveryClient.getServices();
        System.out.println("***********" + list + "************");

        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance serviceInstance : serviceInstanceList) {
            System.out.println(serviceInstance.getServiceId() + "\t" +
                    serviceInstance.getHost() + "\t" + serviceInstance.getPort() + "\t" +
                    serviceInstance.getUri() + "\t" + serviceInstance.getMetadata());
        }
        return this.discoveryClient;
    }


}

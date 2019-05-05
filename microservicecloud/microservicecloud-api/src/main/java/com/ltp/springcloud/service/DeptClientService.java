package com.ltp.springcloud.service;

import com.ltp.springcloud.entities.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
//@FeignClient(value = "MICROSERVICECLOUD-DEPT")
@FeignClient(value = "MICROSERVICECLOUD-DEPT",fallbackFactory = DeptClientServiceFallBackFactory.class)
    public interface DeptClientService {
    @PostMapping(value = "/dept/add")
    public boolean add(@RequestBody Dept dept);

    @GetMapping(value = "/dept/get/{id}")
    public Dept get(@PathVariable(value = "id") Long id);

    @GetMapping(value = "/dept/list")
    public List<Dept> list();
}

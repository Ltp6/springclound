package com.ltp.springcloud.controller;


import com.ltp.springcloud.entities.Dept;
import com.ltp.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @PostMapping(value = "/dept/add")
    public Boolean add(@RequestBody Dept dept) {
        return deptService.add(dept);
    }

    @GetMapping(value = "/dept/get/{id}")
    public Dept get(@PathVariable(value = "id") Long id) {
        return deptService.get(id);
    }

    @GetMapping(value = "/dept/list")
    public List<Dept> findAll() {
        return deptService.list();
    }
}

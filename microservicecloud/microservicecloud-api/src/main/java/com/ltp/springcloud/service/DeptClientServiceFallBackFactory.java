package com.ltp.springcloud.service;

import com.ltp.springcloud.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
@Component//必须加该注解
public class DeptClientServiceFallBackFactory implements FallbackFactory<DeptClientService> {

    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public boolean add(Dept dept) {
                return false;
            }

            @Override
            public Dept get(Long id) {
                return new Dept().setDeptno(id).setDname("该ID" + id + "没有对应的信息consumer客户端提供降级信息，此刻provider服务已关闭")
                        .setDb_source("No this database in Mysql");
            }

            @Override
            public List<Dept> list() {
                return null;
            }
        };
    }
}

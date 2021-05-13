package com.example.netty.server.service.iml;

import com.example.netty.server.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "你好啊，"+name;
    }
}

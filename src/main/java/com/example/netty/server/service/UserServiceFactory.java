package com.example.netty.server.service;

import com.example.netty.server.service.iml.UserServiceIml;

public abstract class UserServiceFactory {
    private static UserService userService = new UserServiceIml();

    public static UserService getUserService() {
        return userService;
    }
}

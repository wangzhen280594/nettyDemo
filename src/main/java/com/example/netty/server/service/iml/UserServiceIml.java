package com.example.netty.server.service.iml;

import com.example.netty.server.service.UserService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserServiceIml implements UserService {

    //正式环境：从数据库查找数据
    private Map<String, String> allUserMap = new ConcurrentHashMap<>();
    {
        allUserMap.put("zhangsan", "123");
        allUserMap.put("lisi", "123");
        allUserMap.put("wangwu", "123");
        allUserMap.put("zhaoliu", "123");
        allUserMap.put("qianqi", "123");
    }

    @Override
    public boolean login(String userName, String passWord) {
        String pass = allUserMap.get(userName);
        if (pass == null) {
            return false;
        }
        return pass.equals(passWord);
    }
}

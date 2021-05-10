package com.example.netty;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SpringBootTest
class NettyApplicationTests {

    @Test
    void contextLoads() {
        String os = System.getProperty("os.name");
        String ss=os.substring(0,3).toLowerCase(Locale.ROOT);
        if("mac".equals(ss)){
            System.out.println(
                    "success"
            );
        }
        else{
            System.out.println(
                    "fail"
            );
        }
    }
}

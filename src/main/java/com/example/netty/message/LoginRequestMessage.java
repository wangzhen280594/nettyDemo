package com.example.netty.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestMessage extends Message{
    private String username;
    private String password;



    @Override
    public int getMessageType() {
        return LoginRequestMessage;
    }
}

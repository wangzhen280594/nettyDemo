package com.example.netty.server.handler;

import com.example.netty.message.LoginRequestMessage;
import com.example.netty.message.LoginResponseMessage;
import com.example.netty.server.service.UserService;
import com.example.netty.server.service.UserServiceFactory;
import com.example.netty.server.service.iml.UserServiceIml;
import com.example.netty.server.session.Session;
import com.example.netty.server.session.SessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;

@ChannelHandler.Sharable
public class LoginRequestMessageHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
        String username=msg.getUsername();
        String password=msg.getPassword();
        boolean login = UserServiceFactory.getUserService().login(username,password);
        LoginResponseMessage message;
        if(login){
            SessionFactory.getSession().bind(ctx.channel(),username);
            message=new LoginResponseMessage(true,"登陆成功");
        }else {
            message=new LoginResponseMessage(false,"用户名或密码不正确");
        }
        ctx.writeAndFlush(message);
    }
}

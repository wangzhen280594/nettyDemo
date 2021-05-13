package com.example.netty.server.handler;

import com.example.netty.message.RpcRequestMessage;
import com.example.netty.message.RpcResponseMessage;
import com.example.netty.server.service.HelloService;
import com.example.netty.server.service.ServicesFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;


@Slf4j
@ChannelHandler.Sharable
public class RpcRequestMessageHandler extends SimpleChannelInboundHandler<RpcRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequestMessage msg) throws Exception {
        RpcResponseMessage response=new RpcResponseMessage();
        response.setSequenceId(msg.getSequenceId());
       try{
           HelloService service=(HelloService) ServicesFactory.getService(Class.forName(msg.getInterfaceName()));
           Method method=service.getClass().getMethod(msg.getMethodName(),msg.getParameterTypes());
           Object invoke=method.invoke(service,msg.getParameterValue());
           response.setReturnValue(invoke);
       }catch (Exception e){
           e.printStackTrace();
           String message=e.getCause().getMessage();
           response.setExceptionValue(new Exception("远程调用出错"+message));
       }
       ctx.writeAndFlush(response);
    }
}

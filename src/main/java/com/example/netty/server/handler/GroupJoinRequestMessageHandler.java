package com.example.netty.server.handler;

import com.example.netty.message.GroupJoinRequestMessage;
import com.example.netty.message.GroupJoinResponseMessage;
import com.example.netty.server.session.Group;
import com.example.netty.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class GroupJoinRequestMessageHandler extends SimpleChannelInboundHandler<GroupJoinRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupJoinRequestMessage msg) throws Exception {
        Group group= GroupSessionFactory.getGroupSession().joinMember(msg.getGroupName(),msg.getUsername());
        if(group != null){
            ctx.writeAndFlush(new GroupJoinResponseMessage(true,msg.getGroupName()+"群加入成功"));
        }else{
            ctx.writeAndFlush(new GroupJoinResponseMessage(false,msg.getGroupName()+"群不存在"));
        }
    }
}

package com.example.netty.server.handler;

import com.example.netty.message.GroupChatRequestMessage;
import com.example.netty.message.GroupChatResponseMessage;
import com.example.netty.server.session.GroupSessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

@ChannelHandler.Sharable
public class GroupChatRequestMessageHandler extends SimpleChannelInboundHandler<GroupChatRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupChatRequestMessage msg) throws Exception {
      List<Channel> channelList= GroupSessionFactory.getGroupSession().getMembersChannel(msg.getGroupName());
      for(Channel channel:channelList){
          channel.writeAndFlush(new GroupChatResponseMessage(msg.getFrom(),msg.getContent()));
      }
    }
}

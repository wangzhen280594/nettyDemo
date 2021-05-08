package com.example.netty.test;

import com.example.netty.message.LoginRequestMessage;
import com.example.netty.protocol.MessageCodec;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;

public class TestMessageCodec {
    public static void main(String[] args) throws Exception {
        LoggingHandler LOG_HANDLER=new LoggingHandler();
        EmbeddedChannel channel=new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(1024,12,4,0,0),
                LOG_HANDLER,
                new MessageCodec()
                );
        //encode
        LoginRequestMessage message=new LoginRequestMessage("zhangsan","123");
        channel.writeOutbound(message);

        //decode
        ByteBuf buf= ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null,message,buf);
        channel.writeInbound(buf);

    }
}

package com.example.netty.protocol;

import com.example.netty.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

@Slf4j
public class MessageCodec extends ByteToMessageCodec<Message> {


    @Override
    public void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {
        //1.4字节的魔数
        byteBuf.writeBytes(new byte[]{1,2,3,4});
        //2.1字节的版本
        byteBuf.writeByte(1);
        //3.1字节的序列化方式jdk0,json1
        byteBuf.writeByte(0);
        //4.1字节的指令类型
        byteBuf.writeByte(message.getMessageType());
        //5.4字节的请求序号
        byteBuf.writeInt(message.getSequenceId());
        byteBuf.writeByte(0xff);//对齐填充使用
        //6。获取内容的字节数组
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(bos);
        oos.writeObject(message);
        byte[] bytes=bos.toByteArray();
        //7.长度
        byteBuf.writeInt(bytes.length);
        //8.写入内容
        byteBuf.writeBytes(bytes);
    }

    @Override
    public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int magicNum = byteBuf.readInt();
        byte version=byteBuf.readByte();
        byte serializerType= byteBuf.readByte();
        byte messageType =byteBuf.readByte();
        int sequenceId=byteBuf.readInt();
        byteBuf.readByte();
        int length=byteBuf.readInt();
        byte[] bytes=new byte[length];
        byteBuf.readBytes(bytes,0,length);
        ObjectInputStream ois=new ObjectInputStream(new ByteArrayInputStream(bytes));
        Message message=(Message)ois.readObject();
        log.debug("{},{},{},{},{},{}",magicNum,version,serializerType,messageType,sequenceId,length);
        log.debug("{}",message);
        list.add(message);
    }

}

package com.example.netty.server.session;

import com.example.netty.server.session.iml.GroupSessionMemoryImpl;

public class GroupSessionFactory {
  private static GroupSession session=new GroupSessionMemoryImpl();

  public static GroupSession getGroupSession(){return session;}
}

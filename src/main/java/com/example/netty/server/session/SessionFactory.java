package com.example.netty.server.session;

import com.example.netty.server.session.iml.SessionImpl;

public abstract class SessionFactory {
    private static Session session = new SessionImpl();

    public static Session getSession() {
        return session;
    }

}

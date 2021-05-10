package com.example.netty.server.session;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.Set;

@Data
@AllArgsConstructor
public class Group {
    private String name;

    private Set<String> members;

    public static final Group EMPTY_GROUP = new Group("empty", Collections.emptySet());
}

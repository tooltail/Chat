package com.alex.chat.service;

import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.UUID;

@Service
public class UserService {


    public static @Nonnull String nextId() {

        return UUID.randomUUID().toString();
    }
}

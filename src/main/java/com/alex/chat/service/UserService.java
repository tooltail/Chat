package com.alex.chat.service;

import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.UUID;

@Service
public class UserService {

    public static String USER__ID;

    public @Nonnull String nextUserId() {

        this.USER__ID = UUID.randomUUID().toString();

        return USER__ID;
    }
}

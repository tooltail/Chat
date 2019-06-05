package com.alex.chat.service;

import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.UUID;

@Service
public class UserSevice {

    public @Nonnull String nextUserId() {
        return UUID.randomUUID().toString();
    }
}

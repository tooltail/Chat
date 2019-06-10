package com.alex.chat.dao;

import com.alex.chat.dao.repository.UserMessageRepository;
import com.alex.chat.dao.repository.UserRepository;
import com.alex.chat.data.UserMessagePOJO;
import com.alex.chat.data.UserPOJO;
import com.alex.chat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Repository
public class UserDAO {

    private final Logger LOG                                      = LoggerFactory.getLogger(UserDAO.class);

    private static final String MSG__ADD                          = "ADD {}";

    @Nonnull private final UserRepository userRepository;
    @Nonnull private final UserService userService;
    @Nonnull private final UserMessageRepository userMessageRepository;

    public UserDAO(
            @Nullable @Autowired UserRepository userRepository,
            @Nullable @Autowired UserService userService,
            @Nullable @Autowired UserMessageRepository userMessageRepository) {

        this.userRepository = userRepository;
        this.userService = userService;
        this.userMessageRepository = userMessageRepository;
    }

    @Async
    public @Nullable CompletableFuture<Void> addUser(
            @Nonnull String id,
            @Nonnull String user,
            @Nonnull String password) {

        assert id != null : "<id> is null";
        assert user != null : "<user> is null";
        assert password != null : "<password> is null";

        UserPOJO userPOJO = userRepository.findById(3).get();
        UserMessagePOJO userMessagePOJO= new UserMessagePOJO("eeeeexxxx");

        userPOJO.setMessages(userMessagePOJO);

        userRepository.save(userPOJO);



//        UserMessagePOJO userMessagePOJO = new UserMessagePOJO("exampleMessage");
//
//        UserPOJO userPOJO = userRepository.findById(1).get();
//        userPOJO.addMessage(userMessagePOJO);
//        userMessageRepository.save(userMessagePOJO);

        //userRepository.save(userPOJO);

        LOG.info(
                MSG__ADD,
                userPOJO);

        return CompletableFuture.completedFuture(null);
    }

}

















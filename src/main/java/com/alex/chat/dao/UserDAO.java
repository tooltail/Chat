package com.alex.chat.dao;

import com.alex.chat.dao.repository.UserRepository;
import com.alex.chat.data.UserConversationPOJO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public class UserDAO {

    private final Logger LOG                                 = LoggerFactory.getLogger(UserDAO.class);

    private static final String MSG__ADD                          = "ADD {}";

    @Nonnull private final UserRepository userRepository;
    @Nonnull private final UserService userService;

    public UserDAO(
            @Nullable @Autowired UserRepository userRepository,
            @Nullable @Autowired UserService userService) {

        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Async
    public @Nonnull CompletableFuture<UserPOJO> addUser(
            @Nonnull String id,
            @Nonnull String username,
            @Nonnull String password) {

        assert id != null : "<id> is null";
        assert username != null : "<username> is null";
        assert password != null : "<password> is null";

        List<UserConversationPOJO> conversation = new ArrayList<>();
        conversation.add(new UserConversationPOJO(userService.nextId(), null, null));

        List<UserMessagePOJO> messages = new ArrayList<>();
        messages.add(new UserMessagePOJO(userService.nextId(), conversation));

        UserPOJO user = new UserPOJO(id, username, password, messages);
        userRepository.save(user);

        LOG.info(
                MSG__ADD,
                user);

        return CompletableFuture.completedFuture(null);
    }

}

















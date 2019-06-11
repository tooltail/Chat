package com.alex.chat.dao;


import com.alex.chat.dao.repository.UserMessageRepository;
//import com.alex.chat.data.UserConversationRepository;
import com.alex.chat.dao.repository.UserRepository;
import com.alex.chat.data.UserConversationPOJO;
import com.alex.chat.data.UserMessagePOJO;
import com.alex.chat.data.UserPOJO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository
public class UserMessageDAO {

    private final Logger LOG                             = LoggerFactory.getLogger(UserMessageDAO.class);

    private static final String MSG_ADD                  = "ADD {}";

    @Nonnull private final UserMessageRepository userMessageRepository;
    @Nonnull private final UserRepository userRepository;

    public UserMessageDAO(
            @Autowired @Nullable UserMessageRepository userMessageRepository,
            @Autowired @Nullable UserRepository userRepository) {

        this.userMessageRepository = userMessageRepository;
        this.userRepository = userRepository;
    }

    @Async
    public @Nonnull CompletableFuture<Void> addMessage(
            Integer user_id,
            Integer receiver_id,
            String message_content) {

        UserConversationPOJO userConversation = new UserConversationPOJO(user_id, message_content);

        UserMessagePOJO userMessage = new UserMessagePOJO();
        userMessage.addConversation(userConversation);
        userConversation = new UserConversationPOJO(receiver_id, "OMG!!!");
        userMessage.addConversation(userConversation);

        Optional<UserPOJO> user = userRepository.findById(user_id);
        user.get().addMessages(userMessage);
        userRepository.save(user.get());

        LOG.info(
                MSG_ADD,
                user
        );

        userMessage = new UserMessagePOJO();
        userConversation = new UserConversationPOJO(user_id, message_content);
        userMessage.addConversation(userConversation);
        userConversation = new UserConversationPOJO(receiver_id, "OMG!!!");
        userMessage.addConversation(userConversation);

        user = userRepository.findById(receiver_id);
        user.get().addMessages(userMessage);
        userRepository.save(user.get());

        LOG.info(
                MSG_ADD,
                user
        );

        return CompletableFuture.completedFuture(null);
    }


}

package com.alex.chat.dao;

import com.alex.chat.dao.repository.UserConversationRepository;
import com.alex.chat.dao.repository.UserMessageRepository;
import com.alex.chat.data.UserConversationPOJO;
import com.alex.chat.data.UserMessagePOJO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository
public class UserConversationDAO {

    private final Logger LOG                    = LoggerFactory.getLogger(UserConversationDAO.class);

    private static final String MSG__ADD        = "ADD {}";

    @Autowired private UserMessageRepository userMessageRepository;

    public CompletableFuture<Void> addReplica(
            Integer user_id,
            Integer receiver_id,
            String message_content) {

        UserConversationPOJO userConversation = new UserConversationPOJO(user_id, "AnotherMessage");

        Optional<UserMessagePOJO> userMessage = userMessageRepository.findById(1);

        userMessage.get().addConversation(userConversation);

        userConversation = new UserConversationPOJO(receiver_id, "AnotherOMG!!!");

        userMessage.get().addConversation(userConversation);

        userMessageRepository.save(userMessage.get());

        LOG.info(
                MSG__ADD,
                userMessage
        );

        userConversation = new UserConversationPOJO(receiver_id, "AnotherOMG!!!");

        userMessage = userMessageRepository.findById(2);

        userMessage.get().addConversation(userConversation);

        userConversation = new UserConversationPOJO(user_id, "AnotherMessage");

        userMessage.get().addConversation(userConversation);

        userMessageRepository.save(userMessage.get());

        LOG.info(
                MSG__ADD,
                userMessage
        );

        return CompletableFuture.completedFuture(null);
    }

}

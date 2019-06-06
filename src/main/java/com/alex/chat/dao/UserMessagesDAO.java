package com.alex.chat.dao;

import com.alex.chat.dao.repository.UserMessagesRepository;
import com.alex.chat.data.UserMessagePOJO;
import com.alex.chat.data.UserMessagesPOJO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public class UserMessagesDAO {

    private final Logger LOG                             = LoggerFactory.getLogger(UserMessagesDAO.class);

    private static final String MSG_ADD                  = "ADD {}";

    @Nonnull private final UserMessagesRepository userMessagesRepository;

    public UserMessagesDAO(
            @Autowired @Nullable UserMessagesRepository userMessagesRepository) {

        this.userMessagesRepository = userMessagesRepository;
    }

    @Async
    public @Nonnull CompletableFuture<Void> addMessages(
            String username_id,
            String receiver_id,
            String message_content) {

        List<UserMessagePOJO> messages = new LinkedList<>();
        messages.add(new UserMessagePOJO(username_id, message_content));

        UserMessagesPOJO userMessages = new UserMessagesPOJO(username_id, messages);
        userMessagesRepository.save(userMessages);

        LOG.info(
                MSG_ADD,
                userMessages);

        userMessages = new UserMessagesPOJO(receiver_id, messages);
        userMessagesRepository.save(userMessages);

        LOG.info(
                MSG_ADD,
                userMessages);

        return CompletableFuture.completedFuture(null);
    }


}

package com.alex.chat.dao.repository;

import com.alex.chat.data.UserConversationPOJO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConversationRepository
        extends CrudRepository<UserConversationPOJO, Integer> {
}

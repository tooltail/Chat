package com.alex.chat.dao.repository;

import com.alex.chat.data.UserMessagesPOJO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMessagesRepository
        extends CrudRepository<UserMessagesPOJO, String> {
}

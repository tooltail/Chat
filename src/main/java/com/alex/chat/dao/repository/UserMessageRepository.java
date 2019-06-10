package com.alex.chat.dao.repository;

import com.alex.chat.data.UserMessagePOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMessageRepository
        extends CrudRepository<UserMessagePOJO, Integer> {
}

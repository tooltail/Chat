package com.alex.chat.dao.repository.impl;

import com.alex.chat.dao.repository.UserMessageRepository;
import com.alex.chat.data.UserMessagePOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import javax.persistence.EntityManager;

@Primary
@Repository
public abstract class UserMessageRepositoryImpl
        extends SimpleJpaRepository<UserMessagePOJO, Integer>
        implements UserMessageRepository {

    public UserMessageRepositoryImpl(
            @Autowired @Nullable EntityManager em) {

        super(UserMessagePOJO.class, em);
    }

}

package com.alex.chat.dao.repository.impl;

import com.alex.chat.dao.repository.UserRepository;
import com.alex.chat.data.UserPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import javax.persistence.EntityManager;

@Primary
@Repository
public abstract class UserRepositoryImpl
    extends SimpleJpaRepository<UserPOJO, Integer>
    implements UserRepository {

    public UserRepositoryImpl(
            @Autowired @Nullable EntityManager em) {

        super(UserPOJO.class, em);
    }
}

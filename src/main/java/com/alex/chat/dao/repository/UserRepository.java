package com.alex.chat.dao.repository;

import com.alex.chat.data.UserPOJO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends CrudRepository<UserPOJO, Long> {
}

package com.alex.chat.api;

import com.alex.chat.dao.UserDAO;
import com.alex.chat.data.UserPOJO;
import com.alex.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Nonnull private final UserDAO userDAO;
    @Nonnull private final UserService userSevice;

    public UserController(
            @Nullable @Autowired UserDAO userDAO,
            @Nullable @Autowired UserService userSevice) {

        this.userDAO = userDAO;
        this.userSevice = userSevice;
    }

    /**
     *
     * @param username
     * @param password
     * @return user id
     */
    @PostMapping(path = "/addUser",
                 produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody @Nonnull ResponseEntity<Map<String, String>> addUser(
            @RequestParam(name = "username", required = true) @Nonnull String username,
            @RequestParam(name = "password", required = true) @Nonnull String password) {


        assert username != null : "<username> is null";
        assert password != null : "<password> is null";

        String userId = userSevice.nextId();

        userDAO.addUser(userId, username, password);

        return ResponseEntity.ok(
                Map.of(UserPOJO.DB__ID, userId));
    }
}

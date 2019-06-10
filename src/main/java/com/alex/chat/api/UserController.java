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
    @Nonnull private final UserService userService;

    public UserController(
            @Nullable @Autowired UserDAO userDAO,
            @Nullable @Autowired UserService userService) {

        this.userDAO = userDAO;
        this.userService = userService;
    }

    /**
     *
     * @param user
     * @param password
     * @return user id
     */
    @PostMapping(path = "/addUser",
                 produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody @Nonnull ResponseEntity<Map<String, String>> addUser(
            @RequestParam(name = "user", required = true) @Nonnull String user,
            @RequestParam(name = "password", required = true) @Nonnull String password) {


        assert user != null : "<user> is null";
        assert password != null : "<password> is null";

        String userId = userService.nextId();

        userDAO.addUser(userId, user, password);

        return ResponseEntity.ok(
                Map.of(UserPOJO.DB__PK__ID, userId));
    }
}

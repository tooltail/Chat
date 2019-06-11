package com.alex.chat.api;

import com.alex.chat.dao.UserMessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;


@RestController
@RequestMapping(path = "/api/message")
public class UserMessageController {

    @Nonnull private final UserMessageDAO userMessageDAO;

    public UserMessageController(
            @Autowired @Nullable UserMessageDAO userMessageDAO) {

        this.userMessageDAO = userMessageDAO;
    }

    @PostMapping(path = "/addMessage",
                 produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody ResponseEntity<Map<String, String>> addMessage(
            @RequestParam("user_id") Integer user_id,
            @RequestParam("receiver_id") Integer receiver_id,
            @RequestParam("message_content") String message_content) {


        userMessageDAO.addMessage(
                user_id,
                receiver_id,
                message_content);

        return ResponseEntity.ok(Map.of("id", "2"));
    }
}

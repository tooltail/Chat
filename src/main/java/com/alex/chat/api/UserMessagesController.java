package com.alex.chat.api;
/*
import com.alex.chat.dao.UserMessagesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/api/message")
public class UserMessagesController {

    @Nonnull private final UserMessagesDAO userMessagesDAO;

    public UserMessagesController(
            @Autowired @Nullable UserMessagesDAO userMessagesDAO) {

        this.userMessagesDAO = userMessagesDAO;
    }

    @PostMapping(path = "/addMessage",
                 produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Map<String, String>>> addMessage(
            @RequestParam("username_id") String username_id,
            @RequestParam("receiver_id") String receiver_id,
            @RequestParam("message_content") String message_content) {


        userMessagesDAO.addMessages(
                username_id,
                receiver_id,
                message_content);

        List<Map<String, String>> response = new ArrayList<>();

        response.add(Map.of("username_id", username_id));
        response.add(Map.of("receiver_id", receiver_id));

        return ResponseEntity.ok(response);
    }
}
*/
package com.alex.chat.api;

import com.alex.chat.dao.UserConversationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/replica")
public class UserReplicaController {

    @Nonnull private final UserConversationDAO userConversationDAO;

    public UserReplicaController(
            @Autowired @Nullable UserConversationDAO userConversationDAO) {

        this.userConversationDAO = userConversationDAO;
    }

    @PostMapping(path = "/addReplica",
                 produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody ResponseEntity<Map<String, String>> addReplica(
            @RequestParam("user_id") Integer user_id,
            @RequestParam("receiver_id") Integer receiver_id,
            @RequestParam("message_content") String message_content) {

        userConversationDAO.addReplica(user_id, receiver_id, message_content);

        return ResponseEntity.ok(Map.of("id", "2"));

    }
}

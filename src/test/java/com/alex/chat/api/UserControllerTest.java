package com.alex.chat.api;

import com.alex.chat.dao.repository.UserConversationRepository;
import com.alex.chat.dao.repository.UserMessageRepository;
import com.alex.chat.dao.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.awaitility.Awaitility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@EnableAsync
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired private UserRepository userRepository;
    @Autowired private MockMvc mvc;
    @Autowired private ObjectMapper jsonMapper;
    @Autowired private UserMessageRepository userMessageRepository;
    @Autowired private UserConversationRepository userConversationRepository;

    private static final String USER = "userExample";
    private static final String PASSWORD = "passwordExample";

    private static final String STATIC__MESSAGE__CONTENT = "hello everyone!!";

    @Test
    @DirtiesContext
    public void addUserTest() throws Exception {

                mvc.perform(post("/api/user/addUser")
                        .param("user", Objects.toString(USER, ""))
                        .param("password", Objects.toString(PASSWORD, ""))
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

                mvc.perform(post("/api/user/addUser")
                        .param("user", Objects.toString(USER, ""))
                        .param("password", Objects.toString(PASSWORD, ""))
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));


        Awaitility.await()
                .timeout(10, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .until(
                        () -> userRepository.findById(2).orElse(null),
                        Objects::nonNull);

    }

    @Test
    @DirtiesContext
    public void addMessageTest() throws Exception {

                mvc.perform(post("/api/message/addMessage")
                        .param("user_id", "1")
                        .param("receiver_id", "2")
                        .param("message_content", STATIC__MESSAGE__CONTENT)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Awaitility.await()
                .timeout(10, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .until(
                        () -> userMessageRepository.findById(2).orElse(null),
                        Objects::nonNull);

    }

    @Test
    @DirtiesContext
    public void addConversationTest() throws Exception {

                mvc.perform(post("/api/replica/addReplica")
                .param("user_id", "1")
                .param("receiver_id", "2")
                .param("message_content", "Another message")
                .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));


        Awaitility.await()
                .timeout(10, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .until(
                        () -> userConversationRepository.findById(4).orElse(null),
                        Objects::nonNull
                );
    }


}

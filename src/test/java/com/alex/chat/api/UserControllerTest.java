package com.alex.chat.api;

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

import java.util.Map;
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
    @Autowired private UserMessageRepository userMessagesRepository;

    private static final String USERNAME = "usernameExample";
    private static final String PASSWORD = "passwordExample";

    // static id
    private static String STATIC__ID;
    private static final String STATIC__RECEIVER__ID = "7d6b80dc-fd1b-4b5f-a1da-a1f06158a44b";
    private static final String STATIC__MESSAGE__CONTENT = "hello everyone!!";

    @Test
    @DirtiesContext
    public void addUserTest() throws Exception {


        String jsonResp =
                mvc.perform(post("/api/user/addUser")
                        .param("username", Objects.toString(USERNAME, ""))
                        .param("password", Objects.toString(PASSWORD, ""))
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String userId = (String) Objects.requireNonNull(
              jsonMapper.readValue(jsonResp, Map.class)
                        .get("id"));

        this.STATIC__ID = userId;


        Awaitility.await()
                .timeout(20, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .until(
                        () -> userRepository.findById(userId).orElse(null),
                        Objects::nonNull);

    }

    @Test
    @DirtiesContext
    public void addMessageTest() throws Exception {

        String jsonResp =
                mvc.perform(post("/api/message/addMessage")
                        .param("username_id", STATIC__ID)
                        .param("receiver_id", STATIC__RECEIVER__ID)
                        .param("message_content", STATIC__MESSAGE__CONTENT)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();


        Awaitility.await()
                .timeout(20, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .until(
                        () -> userMessagesRepository.findById(STATIC__ID).orElse(null),
                        Objects::nonNull);

    }


}

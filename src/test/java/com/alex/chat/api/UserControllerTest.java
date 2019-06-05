package com.alex.chat.api;

import com.alex.chat.dao.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.Objects;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    private static final String USERNAME = "usernameExample";
    private static final String PASSWORD = "passwordExample";

    @Test
    @DirtiesContext
    public void test() throws Exception {


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

        String jobId = (String)Objects.requireNonNull(
              jsonMapper.readValue(jsonResp, Map.class)
                        .get("id"));

        System.out.println("======================================"+jobId+"=========================================");
    }


}

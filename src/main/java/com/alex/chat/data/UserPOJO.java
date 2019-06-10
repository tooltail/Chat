package com.alex.chat.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.style.ToStringCreator;
import org.springframework.data.annotation.Immutable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = UserPOJO.DB__TABLE)
public class UserPOJO {

    static final String DB__TABLE                  = "user";

    public static final String DB__PK__ID          = "id";
    static final String DB__USER                   = "user";
    static final String DB__PASSWORD               = "password";
    static final String DB__MESSAGES               = "messages";

    static final String JSON__ID                   = DB__PK__ID;
    static final String JSON__USER                 = DB__USER;
    static final String JSON__PASSWORD             = DB__PASSWORD;
    static final String JSON__MESSAGES             = DB__MESSAGES;

    @Id
    @Column(name = DB__PK__ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Nullable private Integer id;

    @Column(name = DB__USER)
    @Nullable private String user;

    @Column(name = DB__PASSWORD)
    @Nullable private String password;

    @OneToMany(mappedBy = UserMessagePOJO.DB__FK__USER, cascade = CascadeType.ALL)
    @Nullable private Set<UserMessagePOJO> messages;


    protected UserPOJO() {}

    @JsonCreator
    public UserPOJO(
            //@JsonProperty(JSON__ID) @Nullable String id,
            @JsonProperty(JSON__USER) @Nullable String user,
            @JsonProperty(JSON__PASSWORD) @Nullable String password)
            //@JsonProperty(JSON__MESSAGES) @Nullable List<UserMessagePOJO> messages)
            {


        //this.id = id;
        this.user = user;
        this.password = password;
       // this.messages = List.copyOf(messages);
    }

    public void setMessages(UserMessagePOJO message) {
        Set<UserMessagePOJO> userMessagePOJOS = new HashSet<>();
        userMessagePOJOS.add(message);
        this.messages = userMessagePOJOS;
        message.setUser(this);
    }

    //    public void addMessage(UserMessagePOJO userMessage) {
//
//        this.messages.add(userMessage);
//        userMessage.setUser(this);
//        }
//
//
//    public Set<UserMessagePOJO> getMessages() {
//        return messages;
//    }

}
























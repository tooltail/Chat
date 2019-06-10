package com.alex.chat.data;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.style.ToStringCreator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = UserMessagePOJO.DB__TABLE)
public class UserMessagePOJO {

    static final String DB__TABLE                        = "user_message";

    static final String DB__PK__ID                       = "id";
    static final String DB__CONVERSATION                 = "conversation";
    private static final String DB__USER__ID             = "user_id";
    public static final String DB__FK__USER              = "user";

    static final String JSON__MESSAGE__ID                = DB__PK__ID;
    static final String JSON__CONVERSATION               = DB__CONVERSATION;

    @Id
    @Column(name = DB__PK__ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Nullable private Integer id;


    @ManyToOne
    @JoinColumn(name = DB__USER__ID)
    @Nullable protected UserPOJO user;

    @Column(name = "example")
    @Nullable private String example;


    //@OneToMany(mappedBy = UserConversationPOJO.DB__FK__MESSAGE)
    //@Nullable private volatile List<UserConversationPOJO> conversation;

    protected UserMessagePOJO() {}

    //@JsonCreator
    public UserMessagePOJO(
            //@JsonProperty(JSON__MESSAGE__ID) @Nullable Long id,
            String example)
            //@JsonProperty(JSON__CONVERSATION) @Nullable List<UserConversationPOJO> conversation)
    {

        this.example = example;
        //this.id = id;

//        if (conversation == null) {
//            this.conversation = null;
//        } else {
//            this.conversation = List.copyOf(conversation);
//        }
    }

    public UserPOJO getUser() {
        return user;
    }

    public void setUser(UserPOJO user) {
        this.user = user;
    }

}






















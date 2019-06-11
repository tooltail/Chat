package com.alex.chat.data;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.style.ToStringCreator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


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

    @OneToMany(mappedBy = UserConversationPOJO.DB__FK__MESSAGE, cascade = CascadeType.ALL)
    @Nullable private Set<UserConversationPOJO> conversation = new HashSet<>();

    public UserMessagePOJO() {}


    public UserPOJO getUser() {
        return user;
    }

    public void setUser(UserPOJO user) {
        this.user = user;
    }

    public void addConversation(UserConversationPOJO userConversationPOJO) {
        //Set<UserConversationPOJO> userConversationPOJOS = new HashSet<>();
        //userConversationPOJOS.add(userConversationPOJO);
        this.conversation.add(userConversationPOJO);
        userConversationPOJO.setMessage(this);
    }

}






















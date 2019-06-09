package com.alex.chat.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.style.ToStringCreator;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.persistence.*;

// change all messages onto conversation
@Immutable
@Entity
@Table(name = UserConversationPOJO.DB__TABLE)
public class UserConversationPOJO {

    // table name
    static final String DB__TABLE                       = "user_conversation";

    // field names
    static final String DB__CONVERSATION__ID            = "conversation_id";
    static final String DB__USER__ID                    = "user_id";
    static final String DB__USER__MESSAGE__CONTENT      = "user_message_content";
    static final String DB__MESSAGE__ID                 = "message_id";

    static final String JSON__CONVERSATION__ID          = DB__CONVERSATION__ID;
    static final String JSON__USER__ID                  = DB__USER__ID;
    static final String JSON__USER__MESSAGE_CONTENT     = DB__USER__MESSAGE__CONTENT;


    // UUID generator
    @Id
    @Column(name = DB__CONVERSATION__ID)
    @Nullable private volatile String conversation_id;

    // id several username(message history between several users)
    @Column(name = DB__USER__ID)
    @Nullable private volatile String user_id;


    @Column(name = DB__USER__MESSAGE__CONTENT)
    @Nullable private volatile String user_message_content;


    @ManyToOne
    @JoinColumn(name = DB__MESSAGE__ID)
    @Nullable private volatile UserMessagePOJO message_id;

    protected UserConversationPOJO() {}

    public UserConversationPOJO(
            @JsonProperty(JSON__CONVERSATION__ID) @Nullable String conversation_id,
            @JsonProperty(JSON__USER__ID) @Nullable String user_id,
            @JsonProperty(JSON__USER__MESSAGE_CONTENT) @Nullable String user_message_content) {

        this.conversation_id = conversation_id;
        this.user_id = user_id;
        this.user_message_content = user_message_content;
    }

    @JsonProperty(JSON__CONVERSATION__ID)
    public @Nullable String conversation_id() {
        return conversation_id;
    }

    @JsonProperty(JSON__USER__ID)
    public @Nullable String user_id() {
        return user_id;
    }

    @JsonProperty(JSON__USER__MESSAGE_CONTENT)
    public @Nullable String user_message_content() {
        return user_message_content;
    }

    @Override
    public @Nullable String toString() {

        return new ToStringCreator(this)
                .append(JSON__CONVERSATION__ID, conversation_id())
                .append(JSON__USER__ID, user_id())
                .append(JSON__USER__MESSAGE_CONTENT, user_message_content())
                .toString();
    }

}




















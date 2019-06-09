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


@Immutable
@Entity
@Table(name = UserMessagePOJO.DB__TABLE)
public class UserMessagePOJO {

    static final String DB__TABLE                        = "user_message";

    static final String DB__MESSAGE__ID                  = "message_id";
    public static final String DB__USER__ID              = "user_id";
    static final String DB__CONVERSATION                 = "conversation";

    static final String JSON__MESSAGE__ID                = DB__MESSAGE__ID;
    static final String JSON__CONVERSATION               = DB__CONVERSATION;

    // create with UUID creator
    @Id
    @Column(name = DB__MESSAGE__ID, nullable = false)
    @Nullable private volatile String message_id;


    @ManyToOne
    @JoinColumn(name = DB__USER__ID)
    @Nullable private volatile UserPOJO user_id;


    // no need to create column
    @OneToMany(targetEntity = UserConversationPOJO.class, mappedBy = UserConversationPOJO.DB__MESSAGE__ID)
    // maybe null
    //@Column(name = DB__CONVERSATION)
    @Nullable private volatile List<UserConversationPOJO> conversation;

    protected UserMessagePOJO() {}

    @JsonCreator
    public UserMessagePOJO(
            @JsonProperty(JSON__MESSAGE__ID) @Nonnull String message_id,
            @JsonProperty(JSON__CONVERSATION) @Nullable List<UserConversationPOJO> conversation) {

        assert message_id != null : "<message_id> is null";

        this.message_id = message_id;
        if (conversation == null) {
            this.conversation = null;
        } else {
            this.conversation = List.copyOf(conversation);
        }
    }

    @JsonProperty(JSON__MESSAGE__ID)
    public @Nonnull String message_id() {
        return Objects.requireNonNull(message_id);
    }

    @JsonIgnore
    public @Nullable UserPOJO user_id() {
        return user_id;
    }

    @JsonProperty(JSON__CONVERSATION)
    public @Nullable List<UserConversationPOJO> conversation() {
        return conversation;
    }

    @Override
    public @Nullable String toString() {

        return new ToStringCreator(this)
                .append(JSON__MESSAGE__ID, message_id())
                .append(JSON__CONVERSATION, conversation())
                .toString();
    }


}






















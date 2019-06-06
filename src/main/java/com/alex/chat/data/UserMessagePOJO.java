package com.alex.chat.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.style.ToStringCreator;
import org.springframework.lang.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.persistence.*;
import java.util.Objects;

@Immutable
@Entity
@Table(name = UserMessagePOJO.DB__TABLE)
public class UserMessagePOJO {

    // table name
    static final String DB__TABLE                       = "user_message";

    // field names
    static final String DB__MESSAGE__ID                 = "id";
    static final String DB__USERNAME__ID                = "username_id";
    static final String DB__USERNAME__MESSAGE_LIST      = "username_message_list";
    static final String DB__OWNER__ID                   = "owner_id";
    static final String DB__OWNER                       = "owner";

    static final String JSON__USERNAME__ID              = DB__USERNAME__ID;
    static final String JSON__USERNAME__MESSAGE_LIST    = DB__USERNAME__MESSAGE_LIST;


    //automatic increment
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DB__MESSAGE__ID, nullable = false)
    @Nullable private volatile Long message_id;

    // id several username(message history between several users)
    @Column(name = DB__USERNAME__ID, nullable = false)
    @Nullable private volatile String username_id;

    // message content(massive of values(String))
    @Column(name = DB__USERNAME__MESSAGE_LIST, nullable = false)
    @Nullable private volatile String message_content;

    // id one username(owner)
    @ManyToOne(optional = false)
    @JoinColumn(name = DB__OWNER__ID, nullable = false)
    @Nullable private volatile UserMessagesPOJO owner;

    protected UserMessagePOJO() {}

    @JsonCreator
    public UserMessagePOJO(
            @JsonProperty(JSON__USERNAME__ID) @Nonnull String username_id,
            @JsonProperty(JSON__USERNAME__MESSAGE_LIST) @NonNull String message_content) {

        assert username_id != null : "<username_id> is null";
        assert message_content != null : "<message_content> is null";

        this.username_id = username_id;
        this.message_content = message_content;
    }

    @JsonProperty(JSON__USERNAME__ID)
    public @NonNull String username_id() {
        return Objects.requireNonNull(username_id);
    }

    @JsonProperty(JSON__USERNAME__MESSAGE_LIST)
    public @NonNull String message_content() {
        return Objects.requireNonNull(message_content);
    }

    @Override
    public @NonNull String toString() {
        return new ToStringCreator(this)
                .append(JSON__USERNAME__ID, username_id())
                .append(JSON__USERNAME__MESSAGE_LIST, message_content())
                .toString();
    }

}

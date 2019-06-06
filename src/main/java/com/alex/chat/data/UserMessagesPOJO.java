package com.alex.chat.data;


import com.alex.chat.service.UserService;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.style.ToStringCreator;
import org.springframework.lang.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Immutable
@Entity
@Table(name = UserMessagesPOJO.DB__TABLE)
public class UserMessagesPOJO {

    static final String DB__TABLE                        = "user_messages";

    static final String DB__USERNAME__ID                 = "username_id";
    static final String DB__USERNAME__MESSAGES__LIST     = "messages";

    static final String JSON__USERNAME__ID               = DB__USERNAME__ID;
    static final String JSON__USERNAME__MESSAGES__LIST   = DB__USERNAME__MESSAGES__LIST;

    // owner id
    // Должен быть id in column
    @Id
    @Column(name = DB__USERNAME__ID, nullable = false)
    @Nullable private volatile String username_id;

    // list of owner messages
    @OneToMany(targetEntity = UserMessagePOJO.class, mappedBy = UserMessagePOJO.DB__OWNER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = DB__USERNAME__MESSAGES__LIST, nullable = false)
    @Nullable private volatile List<UserMessagePOJO> messages;

    protected UserMessagesPOJO() {}

    @JsonCreator
    public UserMessagesPOJO(
            @JsonProperty(JSON__USERNAME__ID) @Nonnull String username_id,
            @JsonProperty(JSON__USERNAME__MESSAGES__LIST) @NonNull List<UserMessagePOJO> messages) {

        assert messages != null : "<messages> is null";
        assert username_id != null : "<username_id> is null";

        // Check unmodifiable list
        this.messages = List.copyOf(messages);
        this.username_id = username_id;
    }

    @JsonProperty(JSON__USERNAME__ID)
    public @Nonnull String username_id() {
        return Objects.requireNonNull(username_id);
    }

    @JsonProperty(JSON__USERNAME__MESSAGES__LIST)
    public @NonNull List<UserMessagePOJO> messages() {
        return Objects.requireNonNull(messages);
    }

    @Override
    public @Nonnull String toString() {
        return new ToStringCreator(this)
                .append(JSON__USERNAME__ID, username_id())
                .toString();
    }

}

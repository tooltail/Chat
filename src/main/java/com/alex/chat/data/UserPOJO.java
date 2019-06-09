package com.alex.chat.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.style.ToStringCreator;
import org.springframework.data.annotation.Immutable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Immutable
@Entity
@Table(name = UserPOJO.DB__TABLE)
public class UserPOJO {

    // table name
    static final String DB__TABLE                  = "user";

    // user id
    public static final String DB__ID              = "id";
    // user name
    static final String DB__USER                   = "user";
    // user password
    static final String DB__PASSWORD               = "password";
    // id all messages
    static final String DB__MESSAGE__ID            = "message_id";

    static final String JSON__ID                   = DB__ID;
    static final String JSON__USER                 = DB__USER;
    static final String JSON__PASSWORD             = DB__PASSWORD;
    static final String JSON__MESSAGE__ID          = DB__MESSAGE__ID;

    // Create with UUID creator
    @Id
    @Column(name = DB__ID, unique = true, nullable = false)
    @Nullable private volatile String id;

    @Column(name = DB__USER, nullable = false)
    @Nullable private volatile String user;

    @Column(name = DB__PASSWORD, nullable = false)
    @Nullable private volatile String password;

    // no need to create column
    //@Column(name = DB__MESSAGE__ID, nullable = false)
    @OneToMany(targetEntity = UserMessagePOJO.class, mappedBy = UserMessagePOJO.DB__USER__ID)
    @Nullable private volatile List<UserMessagePOJO> message_id;


    protected UserPOJO() {}

    @JsonCreator
    public UserPOJO(
            @JsonProperty(JSON__ID) @Nonnull String id,
            @JsonProperty(JSON__USER) @Nonnull String user,
            @JsonProperty(JSON__PASSWORD) @Nonnull String password,
            @JsonProperty(JSON__MESSAGE__ID) @Nonnull List<UserMessagePOJO> message_id) {

        assert id != null : "<id> is null";
        assert user != null : "<user> is null";
        assert password != null : "<password> is null";
        assert message_id != null : "<message_id> is null";

        this.id = id;
        this.user = user;
        this.password = password;
        this.message_id = List.copyOf(message_id);
    }

    @JsonProperty(JSON__ID)
    public @Nonnull String id() {
        return Objects.requireNonNull(id);
    }

    @JsonProperty(JSON__USER)
    public @Nonnull String user() {
        return Objects.requireNonNull(user);
    }

    @JsonProperty(JSON__PASSWORD)
    public @Nonnull String password() {
        return Objects.requireNonNull(password);
    }

    @JsonProperty(JSON__MESSAGE__ID)
    public @Nonnull List<UserMessagePOJO> message_id() {
        return Objects.requireNonNull(message_id);
    }

    @Override
    public @Nonnull String toString() {

        return new ToStringCreator(this)
                .append(JSON__ID, id())
                .append(JSON__USER, user())
                .append(JSON__PASSWORD, password())
                .append(JSON__MESSAGE__ID, message_id())
                .toString();
    }


}
























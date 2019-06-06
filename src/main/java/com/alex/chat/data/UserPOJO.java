package com.alex.chat.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.style.ToStringCreator;
import org.springframework.data.annotation.Immutable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;


// Регистрация пользователя происходит независимо от сообщений
@Immutable
@Entity
@Table(name = UserPOJO.DB__TABLE,
        indexes = { @Index(columnList = UserPOJO.DB__USERNAME),
                    @Index(columnList = UserPOJO.DB__PASSWORD) })
public class UserPOJO {

    static final String DB__TABLE                  = "user";

    public static final String DB__ID              = "id";
    static final String DB__USERNAME               = "username";
    static final String DB__PASSWORD               = "password";
    //static final String DB__MESSAGES__ID           = "messages_id";

    static final String JSON__ID                   = DB__ID;
    static final String JSON__USERNAME             = DB__USERNAME;
    static final String JSON__PASSWORD             = DB__PASSWORD;

    @Id
    @Column(name = DB__ID, unique = true, nullable = false)
    @Nullable private volatile String id;

    @Column(name = DB__USERNAME, nullable = false)
    @Nullable private volatile String username;

    @Column(name = DB__PASSWORD, nullable = false)
    @Nullable private volatile String password;

    /*
    @OneToMany(targetEntity = UserMessagePOJO.class, mappedBy = UserMessagePOJO.DB__USERNAME__ID, cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = DB__MESSAGES__ID, nullable = true)
    @Nullable private volatile List<UserMessagePOJO> messages;
    */

    protected UserPOJO() {}

    @JsonCreator
    public UserPOJO(
            @JsonProperty(JSON__ID) @Nonnull String id,
            @JsonProperty(JSON__USERNAME) @Nonnull String username,
            @JsonProperty(JSON__PASSWORD) @Nonnull String password) {

        assert id != null       : "<id> is null";
        assert username != null : "<username> is null";
        assert password != null : "<password> is null";

        this.id = id;
        this.username = username;
        this.password = password;
        }

        @JsonProperty(JSON__ID)
        protected @Nonnull String id() {
            return Objects.requireNonNull(id);
        }

        @JsonProperty(JSON__USERNAME)
        public @Nonnull String username() {
            return Objects.requireNonNull(username);
        }

        @JsonProperty(JSON__PASSWORD)
        public @Nonnull String password() {
            return Objects.requireNonNull(password);
        }
        /**
        @JsonIgnore
        public @Nullable List<UserMessagePOJO> messages() {
            return messages;
        }
        */
        @Override
        public @Nonnull String toString() {

            return new ToStringCreator(this)
                    .append(JSON__ID, id())
                    .append(JSON__USERNAME, username())
                    .append(JSON__PASSWORD, password())
                    .toString();
        }

}

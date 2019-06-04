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

@Immutable
@Entity
@Table(name = UserPOJO.DB__TABLE,
        indexes = { @Index(columnList = UserPOJO.DB__USERNAME),
                  @Index(columnList = UserPOJO.DB__PASSWORD) })
public class UserPOJO {

    static final String DB__TABLE                  = "user";

    static final String DB__ID                     = "id";
    static final String DB__USERNAME               = "username";
    static final String DB__PASSWORD               = "password";
    static final String DB__MESSAGES               = "messages";

    static final String JSON__USERNAME             = DB__USERNAME;
    static final String JSON__PASSWORD             = DB__PASSWORD;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DB__ID, unique = true, nullable = false)
    @Nullable private volatile Long id;

    @Column(name = DB__USERNAME, nullable = false)
    @Nullable private volatile String username;

    @Column(name = DB__PASSWORD, nullable = false)
    @Nullable private volatile String password;

    @OneToMany(targetEntity = MessagePOJO.class, mappedBy = MessagePOJO.DB__AN__ID, cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = DB__MESSAGES, nullable = false)
    @Nullable private volatile List<MessagePOJO> messages;

    protected UserPOJO() {}

    @JsonCreator
    public UserPOJO(
            @JsonProperty(JSON__USERNAME) @Nonnull String username,
            @JsonProperty(JSON__PASSWORD) @Nonnull String password) {

        assert username != null : "<username> is null";
        assert password != null : "<password> is null";

        this.username = username;
        this.password = password;
        }

        @JsonIgnore
        protected @Nullable Long id() {
            return id;
        }

        @JsonProperty(JSON__USERNAME)
        public @Nonnull String username() {
            return Objects.requireNonNull(username);
        }

        @JsonProperty(JSON__PASSWORD)
        public @Nonnull String password() {
            return Objects.requireNonNull(password);
        }

        @Override
        public @Nonnull String toString() {

            return new ToStringCreator(this)
                    .append(JSON__USERNAME, username())
                    .append(JSON__PASSWORD, password())
                    .toString();
        }

}

























package com.alex.chat.data;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.style.ToStringCreator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.persistence.*;

@Immutable
@Entity
@Table(name = MessagePOJO.DB__TABLE,
        indexes = {@Index(columnList = MessagePOJO.DB__ID),
                   @Index(columnList = MessagePOJO.DB__MESSAGE__CONTENT)})
public class MessagePOJO {

    static final String DB__TABLE                        = "message";

    static final String DB__ID                           = "id";
    static final String DB__MESSAGE__CONTENT             = "message_content";
    public static final String DB__USERNAME__ID          = "username_id";

    static final String JSON__MESSAGE__CONTENT           = DB__MESSAGE__CONTENT;
    static final String JSON__ID                         = DB__ID;


    // message id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DB__ID, unique = true, nullable = false)
    @Nullable private volatile Long id;

    // username id
    @ManyToOne(optional = false)
    //username_id
    @JoinColumn(name = DB__USERNAME__ID, nullable = false)
    @Nullable private volatile UserPOJO username_id;

    // message content
    @Column(name = DB__MESSAGE__CONTENT, nullable = false)
    @Nullable private volatile String message_content;

    protected MessagePOJO() {}

    @JsonCreator
    public MessagePOJO(
            @JsonProperty(JSON__ID) @Nonnull Long id,
            @JsonProperty(JSON__MESSAGE__CONTENT) @Nonnull String message_content) {

        assert id != null : "<id> is null";
        assert message_content != null : "<message_content> is null";

        this.id = id;
        this.message_content = message_content;
    }

    @JsonIgnore
    @Nullable protected UserPOJO username_id() {
        return username_id;
    }

    @JsonProperty(JSON__MESSAGE__CONTENT)
    @Nonnull public String message_content() {
        return message_content;
    }

    @JsonProperty(JSON__ID)
    @Nonnull public Long id() {
        return id;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append(JSON__ID, id())
                .append(JSON__MESSAGE__CONTENT, message_content())
                .toString();
    }

}
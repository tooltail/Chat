//package com.alex.chat.data;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import org.springframework.core.style.ToStringCreator;
//
//import javax.annotation.Nullable;
//import javax.annotation.concurrent.Immutable;
//import javax.persistence.*;
//
//// change all messages onto conversation
//@Immutable
//@Entity
//@Table(name = UserConversationPOJO.DB__TABLE)
//public class UserConversationPOJO {
//
//    static final String DB__TABLE                       = "user_conversation";
//
//    static final String DB__PK__ID                      = "id";
//    static final String DB__USER__ID                    = "user_id";
//    static final String DB__USER__MESSAGE__CONTENT      = "user_message_content";
//    private static final String DB__MESSAGE__ID         = "message";
//    public static final String DB__FK__MESSAGE          = "message";
//
//    static final String JSON__CONVERSATION__ID          = DB__PK__ID;
//    static final String JSON__USER__ID                  = DB__USER__ID;
//    static final String JSON__USER__MESSAGE_CONTENT     = DB__USER__MESSAGE__CONTENT;
//
//
//    @Id
//    @Column(name = DB__PK__ID)
//    @Nullable private volatile String id;
//
//    @Column(name = DB__USER__ID)
//    @Nullable private volatile String user_id;
//
//
//    @Column(name = DB__USER__MESSAGE__CONTENT)
//    @Nullable private volatile String user_message_content;
//
//
//    @ManyToOne
//    @JoinColumn(name = DB__MESSAGE__ID)
//    @Nullable protected volatile UserMessagePOJO message;
//
//    protected UserConversationPOJO() {}
//
//    public UserConversationPOJO(
//            @JsonProperty(JSON__CONVERSATION__ID) @Nullable String id,
//            @JsonProperty(JSON__USER__ID) @Nullable String user_id,
//            @JsonProperty(JSON__USER__MESSAGE_CONTENT) @Nullable String user_message_content) {
//
//        this.id = id;
//        this.user_id = user_id;
//        this.user_message_content = user_message_content;
//    }
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

package app.xenovox.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author: hd.viet
 * @date: 2019/10/14 16:29
 **/
@Document("recent_messages")
public class Message {
    @Id
    private String id;
    private String sender;
    private String content;
    private String type;

    public Message(String sender, String content, String type) {
        this.sender = sender;
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

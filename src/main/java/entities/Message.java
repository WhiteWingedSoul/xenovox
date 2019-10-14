package entities;

/**
 * @author: hd.viet
 * @date: 2019/10/14 16:29
 **/
public class Message {
    private String content;

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

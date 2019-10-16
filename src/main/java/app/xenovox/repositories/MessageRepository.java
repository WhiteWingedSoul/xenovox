package app.xenovox.repositories;

import app.xenovox.entities.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: hd.viet
 * @date: 2019/10/16 7:26
 **/
@Component
public class MessageRepository {
    private List<Message> list = new ArrayList<>();
    private static final int MSG_LIMIT = 100;

    public void add(Message message) {
        while(list.size() >= MSG_LIMIT) {
            list.remove(0);
        }
        list.add(message);
    }

    public List<Message> getRecentMessages() {
        return list;
    }
}

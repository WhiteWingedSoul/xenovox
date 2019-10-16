package app.xenovox.repositories;

import app.xenovox.entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hd.viet
 * @date: 2019/10/16 9:07
 **/
@Component
public class UserRepository {
    private List<User> list = new ArrayList<>();

    public void add(User user) {
        list.add(user);
    }

    public void remove(User user) {
        list.remove(user);
    }

    public List<User> getOnlineUsers() {
        return list;
    }
}

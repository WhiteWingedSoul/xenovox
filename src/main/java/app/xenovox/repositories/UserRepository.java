package app.xenovox.repositories;

import app.xenovox.entities.User;
import org.springframework.stereotype.Component;

import java.util.*;

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

    public void remove(String name) {
        for (Iterator<User> iter = list.listIterator(); iter.hasNext(); ) {
            User user = iter.next();
            if (user.getName().equals(name)) {
                iter.remove();
                break;
            }
        }
    }

    public List<User> getOnlineUsers() {
        return list;
    }
}

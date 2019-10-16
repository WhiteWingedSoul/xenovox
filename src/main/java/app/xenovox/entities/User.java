package app.xenovox.entities;

import java.util.Objects;

/**
 * @author: hd.viet
 * @date: 2019/10/16 13:13
 **/
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name);
    }

    @Override public int hashCode() {
        return Objects.hash(name);
    }
}

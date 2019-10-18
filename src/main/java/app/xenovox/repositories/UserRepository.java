package app.xenovox.repositories;

import app.xenovox.entities.Message;
import app.xenovox.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: hd.viet
 * @date: 2019/10/18 8:30
 **/
@Repository
public interface UserRepository extends MongoRepository<User, String> {
}

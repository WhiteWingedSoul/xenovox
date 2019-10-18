package app.xenovox.repositories;

import app.xenovox.entities.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: hd.viet
 * @date: 2019/10/18 8:30
 **/
@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
}

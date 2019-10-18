package app.xenovox.configs;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author: hd.viet
 * @date: 2019/10/18 11:47
 **/
@Configuration
@EnableMongoRepositories(basePackages = "app.xenovox.repositories")
public class DataMongoDBConfig {
    @Value("${MONGODB_URI}")
    private String uri;

    @Bean
    public MongoDbFactory mongoDbFactory()
    {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(uri));
        return new SimpleMongoDbFactory(mongoClient, "chat");
    }

    @Bean
    public MongoTemplate mongoTemplate()
    {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }
}

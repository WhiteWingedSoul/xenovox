package app.xenovox.persistences;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author: hd.viet
 * @date: 2019/10/17 16:33
 **/
public class DynamoDBClient {
    @Value("AWS_ACCESS_KEY_ID")
    private String accessKey;
    @Value("AWS_SECRET_ACCESS_KEY")
    private String secretKey;
    @Value("AWS_DEFAULT_REGION")
    private String region;

    private AmazonDynamoDB client;

    public DynamoDBClient() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }


}

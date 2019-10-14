package controllers;

import entities.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author: hd.viet
 * @date: 2019/10/14 16:33
 **/
@Controller
public class MessageController {
    @MessageMapping("/chat")
    @SendTo("/group")
    public Message sendMessage(@Payload Message message) {
        return message;
    }


}

package app.xenovox.controllers;

import app.xenovox.entities.Message;
import app.xenovox.entities.User;
import app.xenovox.repositories.MessageRepository;
import app.xenovox.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author: hd.viet
 * @date: 2019/10/14 16:33
 **/
@RestController
public class MessageController {

    private MessageRepository messageRepository;
    private UserRepository userRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/group")
    public Message sendMessage(@Payload Message message) {
        messageRepository.add(message);
        return message;
    }

    @MessageMapping("/join")
    @SendTo("/topic/group")
    public Message joinRoom(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        userRepository.add(new User(message.getSender()));
        headerAccessor.getSessionAttributes().put("username", message.getSender());

        return message;
    }

    @GetMapping("/messages")
    public List<Message> getRecentMessage() {
        return messageRepository.getRecentMessages();
    }

    @GetMapping("/users")
    public List<User> getOnlineUsers() {
        return userRepository.getOnlineUsers();
    }

    @GetMapping("/ping")
    public String ping() {
        return "OK";
    }
}

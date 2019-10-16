package app.xenovox.controllers;

import app.xenovox.entities.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * @author: hd.viet
 * @date: 2019/10/14 20:44
 **/
@Component
public class WebSocketEventListener {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void onConnected(SessionConnectedEvent event) {
        System.out.println("FUCK ALL I GOT INSIDE");
//        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
//        String username = headers.getUser().getName();

        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void onDisconnected(SessionDisconnectEvent event) {
        System.out.println("FUCK ALL I CAME OUTSIDe");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username != null) {
            logger.info("User Disconnected : " + username);
            Message chatMessage = new Message(username, "", "LEAVE");
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}

package com.dmitryshibunia.receiver;

import com.dmitryshibunia.model.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReciever {

    private final Logger LOGGER = LogManager.getLogger();

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Message message) {
       LOGGER.info("message received" + message);
    }

}



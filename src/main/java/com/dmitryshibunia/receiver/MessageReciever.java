package com.dmitryshibunia.receiver;

import com.dmitryshibunia.model.Message;
import com.dmitryshibunia.repository.EmployeeManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReciever {

    private final Logger LOGGER = LogManager.getLogger();
    private EmployeeManager employeeManager;

    @Autowired
    MessageReciever (EmployeeManager employeeManager){
        this.employeeManager = employeeManager;
    }

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Message message){
        LOGGER.info("message received " + message);

        employeeManager.updateEmployees(message.getFilterFieldName(),
                                       message.getFieldToChangeName(),
                                       message.getFilterFieldValue(),
                                       message.getFieldToChangeValue());
        LOGGER.info("data updated" );
    }

}



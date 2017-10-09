package by.mchs.jms;

import by.mchs.exception.CallDuplicate;
import by.mchs.model.Call;
import by.mchs.model.Message;
import by.mchs.model.User;
import by.mchs.service.CallService;
import by.mchs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderTransactionReceiver {

    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private CallService callServiceImpl;

    @JmsListener(destination = "OrderTransactionQueue", containerFactory = "myFactory")
    public void receiveMessage(Message message) throws CallDuplicate {
        userServiceImpl.saveUser(new User(message.getIdSub(),message.getFirstName(),message.getLastName(),message.getNumber()));
        callServiceImpl.saveCall(new Call(message.getIdCall(),message.getIdTower(),message.getIdSub(),message.getTimeCall(),message.getDistance()));
    }
}
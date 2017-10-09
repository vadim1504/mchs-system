package by.mchs.controllers;

import by.mchs.model.Call;
import by.mchs.model.Message;
import by.mchs.model.User;
import by.mchs.service.CallService;
import by.mchs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/message")
public class MessageController {

    @Autowired
    private CallService callServiceImpl;
    @Autowired
    private UserService userServiceImpl;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE} )
    ResponseEntity<Void> receiveMessage(@RequestBody Message message) {
        try {
            userServiceImpl.saveUser(new User(message.getIdSub(),message.getFirstName(),message.getLastName(),message.getNumber()));
            callServiceImpl.saveCall(new Call(message.getIdCall(),message.getIdTower(),message.getIdSub(),message.getTimeCall(),message.getDistance()));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}

package by.mchs.controllers;

import by.mchs.exception.UserNotFound;
import by.mchs.model.User;
import by.mchs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE} )
    ResponseEntity<Void> addUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE} )
    ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.allUser();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(params = {"id"},produces = {MediaType.APPLICATION_JSON_VALUE} )
    ResponseEntity<User> getUserById(@RequestParam(value = "id") Integer id) {
        User user = userService.getUser(id);
        if (user==null){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<Void> deleteUser(@RequestParam(value = "id") Integer id) {
        try {
            userService.deleteUser(id);
        } catch (UserNotFound userNotFound) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
        } catch (UserNotFound userNotFound) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(value = "http://localhost:8081")
    @GetMapping(params = {"first_name"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<User>> searchUserByFirstName(@RequestParam(value = "first_name", defaultValue = "") String firstName) {
        List<User> users = userService.getUserByFirstName(firstName);
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @CrossOrigin(value = "http://localhost:8081")
    @GetMapping(params = {"last_name"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<User>> searchUserByLastName(@RequestParam(value = "last_name", defaultValue = "") String lastName) {
        List<User> users = userService.getUserByLastName(lastName);
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @CrossOrigin(value = "http://localhost:8081")
    @GetMapping(params = {"number"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<User>> searchUserByNumber(@RequestParam(value = "number", defaultValue = "") String number) {
        List<User> users = userService.getUserByNumber(number);
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @CrossOrigin(value = "http://localhost:8081")
    @GetMapping(params = {"x1","x2","y1","y2"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<User>> searchUserByRangeCoordinate(@RequestParam(value = "x1", defaultValue = "0") Double x1,@RequestParam(value = "x2", defaultValue = "10000") Double x2,
                                                           @RequestParam(value = "y1", defaultValue = "0") Double y1,@RequestParam(value = "y2", defaultValue = "10000") Double y2) {
        List<User> users = userService.getUserByRangeCoordinate(x1,x2,y1,y2);
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}

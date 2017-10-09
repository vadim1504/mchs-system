package by.mchs.service;

import by.mchs.exception.UserNotFound;
import by.mchs.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> allUser();
    List<User> getUserByFirstName(String first);
    List<User> getUserByLastName(String last);
    List<User> getUserByNumber(String number);
    List<User> getUserByRangeCoordinate(double x1,double x2,double y1,double y2);
    User getUser(int id);
    void saveUser(User user);
    void updateUser(User user) throws UserNotFound;
    void deleteUser(int id) throws UserNotFound;
    void updateCoordinateUser(int id,double x,double y) throws UserNotFound;
}

package by.mchs.service;

import by.mchs.exception.UserNotFound;
import by.mchs.model.User;
import by.mchs.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<User> allUser() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public List<User> getUserByFirstName(String first) {
        return userRepository.getUserByFirstName(first);
    }

    @Override
    @Transactional
    public List<User> getUserByLastName(String last) {
        return userRepository.getUserByLastName(last);
    }

    @Override
    @Transactional
    public List<User> getUserByNumber(String number) {
        return userRepository.getUserByNumber(number);
    }

    @Override
    @Transactional
    public List<User> getUserByRangeCoordinate(double x1, double x2, double y1, double y2) {
        return userRepository.getUserByRangeCoordinate(x1, x2, y1, y2);
    }

    @Override
    @Transactional
    public User getUser(int id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor=UserNotFound.class)
    public void updateUser(User user)throws UserNotFound {
        User updateUser = userRepository.getOne(user.getId());
        if(updateUser==null)
            throw new UserNotFound();
        updateUser.setCoordinateX(user.getCoordinateX());
        updateUser.setCoordinateY(user.getCoordinateY());
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setNumber(user.getNumber());
    }

    @Override
    @Transactional(rollbackFor=UserNotFound.class)
    public void updateCoordinateUser(int id, double x, double y) throws UserNotFound {
        User updateUser = userRepository.getOne(id);
        if(updateUser==null)
            throw new UserNotFound();
        updateUser.setCoordinateX(x);
        updateUser.setCoordinateY(y);
    }

    @Override
    @Transactional(rollbackFor=UserNotFound.class)
    public void deleteUser(int id)throws UserNotFound {
        User deleteUser = userRepository.getOne(id);
        if(deleteUser==null)
            throw new UserNotFound();
        userRepository.delete(deleteUser);
    }
}

package by.mchs;

import by.mchs.exception.CallNotFound;
import by.mchs.exception.UserNotFound;
import by.mchs.model.Call;
import by.mchs.service.CallService;
import by.mchs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
public class CalculateCoordinateApplication implements ApplicationRunner {

    @Autowired
    private CallService callService;
    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(CalculateCoordinateApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        while (true) {
            List<Call> callList = callService.getAllCallByOneUser();
            if (callList.size() > 0) {
                Optional<Call> first = callList.stream().findFirst();
                if (first.isPresent()) {
                    ArrayList<Call> stream = (ArrayList<Call>) callList.stream().filter(call -> call.getIdCall() == first.get().getIdCall()).collect(Collectors.toList());
                    Point point = Calculate.getCoordinate(stream);
                    try {
                        userService.updateCoordinateUser(first.get().getIdUser(), point.getX(), point.getY());
                        stream.forEach(call ->
                        {
                            try {
                                callService.deleteCall(call.getId());
                            } catch (CallNotFound callNotFound) {
                                callNotFound.printStackTrace();
                            }

                        });
                    }catch (UserNotFound userNotFound){
                        userNotFound.printStackTrace();
                    }
                }
            }
        }
    }
}

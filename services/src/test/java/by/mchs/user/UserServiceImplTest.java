package by.mchs.user;

import by.mchs.model.User;
import by.mchs.repository.UserRepository;
import by.mchs.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @TestConfiguration
    static class UserServiceImplTestConfiguration{
        @Bean
        public UserServiceImpl userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    private User john = new User(1,"john","john","1",1000,1000);
    private User alex = new User(2,"alex","alex","2",2000,2000);
    private String wrong_first_name = "wrong_first_name";
    private String wrong_last_name = "wrong_last_name";
    private int wrong_id = 99;
    private String wrong_number = "66";
    private int wrong_x1 = 100;
    private int wrong_x2 = 200;
    private int wrong_y1 = 100;
    private int wrong_y2 = 200;

    @Before
    public void setUp() {
        List<User> allUsers = Arrays.asList(john, alex);

        Mockito.when(userRepository.getUserByFirstName(john.getFirstName()))
                .thenReturn(Collections.singletonList(john));
        Mockito.when(userRepository.getUserByLastName(alex.getLastName()))
                .thenReturn(Collections.singletonList(alex));
        Mockito.when(userRepository.getUserByFirstName(wrong_first_name))
                .thenReturn(null);
        Mockito.when(userRepository.getUserByLastName(wrong_last_name))
                .thenReturn(null);
        Mockito.when(userRepository.getUserByNumber(john.getNumber()))
                .thenReturn(Collections.singletonList(john));
        Mockito.when(userRepository.getUserByNumber(wrong_number))
                .thenReturn(null);
        Mockito.when(userRepository.findOne(john.getId()))
                .thenReturn(john);
        Mockito.when(userRepository.findAll())
                .thenReturn(allUsers);
        Mockito.when(userRepository.findOne(wrong_id))
                .thenReturn(null);
        Mockito.when(userRepository.getUserByRangeCoordinate(john.getCoordinateX(), john.getCoordinateX(),john.getCoordinateY(),john.getCoordinateY()))
                .thenReturn(Collections.singletonList(john));
        Mockito.when(userRepository.getUserByRangeCoordinate(wrong_x1,wrong_x2,wrong_y1,wrong_y2))
                .thenReturn(null);
    }

    @Test
    public void shouldFetchUserByFirstName() {
        User user = userService.getUserByFirstName(john.getFirstName()).get(0);
        assertThat(user.getFirstName()).isEqualTo(john.getFirstName());
    }

    @Test
    public void whenInValidFirstName_thenUserShouldNotBeFound() {
        List<User> user = userService.getUserByFirstName(wrong_first_name);
        assertThat(user).isNull();
        verifyFindByFirstNameIsCalledOnce(wrong_first_name);
    }

    @Test
    public void shouldFetchUserByLastName(){
        User user = userService.getUserByLastName(alex.getLastName()).get(0);
        assertThat(user.getLastName()).isEqualTo(alex.getLastName());
    }

    @Test
    public void whenInValidLastName_thenUserShouldNotBeFound() {
        List<User> user = userService.getUserByLastName(wrong_last_name);
        assertThat(user).isNull();
        verifyFindByLastNameIsCalledOnce(wrong_last_name);
    }

    @Test
    public void shouldFetchUserByNumber(){
        User user = userService.getUserByNumber(john.getNumber()).get(0);
        assertThat(user.getNumber()).isEqualTo(john.getNumber());
    }

    @Test
    public void whenInValidNumber_thenUserShouldNotBeFound() {
        List<User> user = userService.getUserByNumber(wrong_number);
        assertThat(user).isNull();
        verifyFindByNumberIsCalledOnce(wrong_number);
    }

    @Test
    public void shouldFetchUserById(){
        User user = userService.getUser(john.getId());
        assertThat(user.getId()).isEqualTo(john.getId());
    }

    @Test
    public void whenInValidId_thenUserShouldNotBeFound() {
        User user = userService.getUser(wrong_id);
        assertThat(user).isNull();
        verifyFindByIdIsCalledOnce(wrong_id);
    }

    @Test
    public void shouldFetchUserByCoordinate(){
        User user = userService.getUserByRangeCoordinate(john.getCoordinateX(), john.getCoordinateX(),john.getCoordinateY(),john.getCoordinateY()).get(0);
        assertThat(user.getCoordinateX()).isEqualTo(john.getCoordinateX());
        assertThat(user.getCoordinateY()).isEqualTo(john.getCoordinateY());
    }

    @Test
    public void whenInValidCoordinate_thenUserShouldNotBeFound(){
        List<User> user = userService.getUserByRangeCoordinate(wrong_x1,wrong_x2,wrong_y1,wrong_y2);
        assertThat(user).isNull();
        verifyFindByCoordinateIsCalledOnce(wrong_x1,wrong_x2,wrong_y1,wrong_y2);
    }

    @Test
    public void shouldFetchAllUser(){
        List<User> users = userService.allUser();
        verifyFindAllUserIsCalledOnce();
        assertThat(users).hasSize(2)
                .extracting(User::getFirstName)
                .contains(alex.getFirstName(), john.getFirstName());
    }

    private void verifyFindByFirstNameIsCalledOnce(String firstName) {
        Mockito.verify(userRepository, VerificationModeFactory.times(1))
                .getUserByFirstName(firstName);
        Mockito.reset(userRepository);
    }

    private void verifyFindByLastNameIsCalledOnce(String lastName) {
        Mockito.verify(userRepository, VerificationModeFactory.times(1))
                .getUserByLastName(lastName);
        Mockito.reset(userRepository);
    }

    private void verifyFindByNumberIsCalledOnce(String number) {
        Mockito.verify(userRepository, VerificationModeFactory.times(1))
                .getUserByNumber(number);
        Mockito.reset(userRepository);
    }

    private void verifyFindByIdIsCalledOnce(int id) {
        Mockito.verify(userRepository, VerificationModeFactory.times(1))
                .findOne(id);
        Mockito.reset(userRepository);
    }

    private void verifyFindAllUserIsCalledOnce() {
        Mockito.verify(userRepository, VerificationModeFactory.times(1))
                .findAll();
        Mockito.reset(userRepository);
    }

    private void verifyFindByCoordinateIsCalledOnce(int wrong_x1,int wrong_x2,int wrong_y1,int wrong_y2) {
        Mockito.verify(userRepository, VerificationModeFactory.times(1))
                .getUserByRangeCoordinate(wrong_x1,wrong_x2,wrong_y1,wrong_y2);
        Mockito.reset(userRepository);
    }
}

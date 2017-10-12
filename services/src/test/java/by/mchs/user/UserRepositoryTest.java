package by.mchs.user;

import by.mchs.model.User;
import by.mchs.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User(1,"first_name","last_name","1234567",1000,5000);
        userRepository.save(user);
    }

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void shouldFetchUserByFirstName(){
        List<User> users = userRepository.getUserByFirstName("first_name");
        assertThat(users.get(0).getFirstName(), is(user.getFirstName()));
    }

    @Test
    public void shouldFetchUserByLastName(){
        List<User> users = userRepository.getUserByLastName("last_name");
        assertThat(users.get(0).getLastName(), is(user.getLastName()));
    }

    @Test
    public void shouldFetchUserByNumber(){
        List<User> users = userRepository.getUserByNumber("1234567");
        assertThat(users.get(0).getNumber(), is(user.getNumber()));
    }

    @Test
    public void shouldFetchUserByCoordinate(){
        List<User> users = userRepository.getUserByRangeCoordinate(999,1001,4999,5001);
        assertThat(users.get(0).getCoordinateX(), is(user.getCoordinateX()));
        assertThat(users.get(0).getCoordinateY(), is(user.getCoordinateY()));
    }
}

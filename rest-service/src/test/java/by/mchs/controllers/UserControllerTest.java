package by.mchs.controllers;

import by.mchs.RestApiApplication;
import by.mchs.model.User;
import by.mchs.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = RestApiApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @After
    public void resetDb() {
        userRepository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateUser() throws Exception {
        User user = new User(1,"first_name","last_name","number");
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(toJson(user)));

        User user1 = userRepository.findOne(user.getId());
        assertThat(user1,is(notNullValue()));
    }

    @Test
    public void givenUsers_whenGetUsers_thenStatus200() throws Exception {
        User john = new User(2,"john","john","1");
        User alex = new User(3,"alex","alex","2");
        userRepository.saveAndFlush(john);
        userRepository.saveAndFlush(alex);

        mockMvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", is(john.getId())))
                .andExpect(jsonPath("$[1].id", is(alex.getId())));

    }

    @Test
    public void givenUser_whenGetUser_thenStatus200() throws Exception {
        User john = new User(3,"john","john","1");
        userRepository.saveAndFlush(john);

        mockMvc.perform(get("/user").param("id",String.valueOf(john.getId())).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(john.getId())))
                .andExpect(jsonPath("$.firstName", is(john.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(john.getLastName())))
                .andExpect(jsonPath("$.number", is(john.getNumber())));
    }

    @Test
    public void deleteUser_whenDeleteUser_thenStatus204() throws Exception {
        User john = new User(4,"john","john","1");
        userRepository.saveAndFlush(john);

        mockMvc.perform(delete("/user").param("id",String.valueOf(john.getId())).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    public void updateUser_whenUpdateUser_thenStatus200() throws Exception {
        User john = new User(5,"john","john","1");
        userRepository.saveAndFlush(john);

        User user1 = userRepository.findOne(john.getId());
        assertThat(user1,is(notNullValue()));

        User johnUpdate = new User(5,"johnUpdate","johnUpdate","2");

        mockMvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON).content(toJson(johnUpdate)));

        john = userRepository.findOne(john.getId());
        assertThat(john.getId(),is(johnUpdate.getId()));
        assertThat(john.getFirstName(),is(johnUpdate.getFirstName()));
        assertThat(john.getLastName(),is(johnUpdate.getLastName()));
        assertThat(john.getNumber(),is(johnUpdate.getNumber()));
    }

    @Test
    public void givenUsers_whenGetUsersByFirstName_thenStatus200() throws Exception {
        User john = new User(6,"john","john","1");
        userRepository.saveAndFlush(john);

        mockMvc.perform(get("/user").param("first_name",john.getFirstName()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(john.getId())))
                .andExpect(jsonPath("$[0].firstName", is(john.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(john.getLastName())))
                .andExpect(jsonPath("$[0].number", is(john.getNumber())));


    }

    @Test
    public void givenUsers_whenGetUsersByLastName_thenStatus200() throws Exception {
        User john = new User(7,"john","john","1");
        userRepository.saveAndFlush(john);

        mockMvc.perform(get("/user").param("last_name",john.getLastName()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(john.getId())))
                .andExpect(jsonPath("$[0].firstName", is(john.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(john.getLastName())))
                .andExpect(jsonPath("$[0].number", is(john.getNumber())));
    }

    @Test
    public void givenUsers_whenGetUsersByNumber_thenStatus200() throws Exception {
        User john = new User(8,"john","john","1");
        userRepository.saveAndFlush(john);

        mockMvc.perform(get("/user").param("number",john.getNumber()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(john.getId())))
                .andExpect(jsonPath("$[0].firstName", is(john.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(john.getLastName())))
                .andExpect(jsonPath("$[0].number", is(john.getNumber())));
    }

    @Test
    public void givenUsers_whenGetUsersByRangeCoordinate_thenStatus200() throws Exception {
        User john = new User(9,"john","john","1",1000,1000);
        userRepository.saveAndFlush(john);

        mockMvc.perform(get("/user")
                .param("x1",String.valueOf(john.getCoordinateX()-1))
                .param("x2",String.valueOf(john.getCoordinateX()+1))
                .param("y1",String.valueOf(john.getCoordinateY()-1))
                .param("y2",String.valueOf(john.getCoordinateY()+1))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(john.getId())))
                .andExpect(jsonPath("$[0].firstName", is(john.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(john.getLastName())))
                .andExpect(jsonPath("$[0].number", is(john.getNumber())))
                .andExpect(jsonPath("$[0].coordinateX", is(john.getCoordinateX())))
                .andExpect(jsonPath("$[0].coordinateY", is(john.getCoordinateY())));
    }


    private byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}

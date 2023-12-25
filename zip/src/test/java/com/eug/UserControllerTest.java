package com.eug;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void controllerLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    void homeCheck() throws Exception {
        UserController homeController = new UserController();
        String homeGreeting = homeController.home();
        assertEquals("User Repository Home Page", homeGreeting);
    }

    @Test
    void emailSearch() throws Exception {
        UserController emailController = new UserController();
        User user = this.userController.findUser("alexander.kondratiev@docusketch.com");
        assertThat(user).isNotNull();
    }




//    @Test
//    void emailSearch(@PathVariable String email) throws Exception {
//        assertThat(this.restTemplate.getForObject("http://localhost:8080/" + email,
//                String.class)).contains(email);
//    }

}

//360 pic from
//
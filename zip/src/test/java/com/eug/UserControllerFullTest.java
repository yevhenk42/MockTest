package com.eug;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
//@WebMvcTest(UserController.class)
class UserControllerFullTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepository repository;

    @Test
    void home() throws Exception {
        RequestBuilder request = get("/home");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("User Repository Home Page", result.getResponse().getContentAsString());
    }

    @Test
    void findUser() throws Exception {
        RequestBuilder request = get("/users/ak_employee_test0@docusketch.com");
        MvcResult result = mvc.perform(request).andReturn();

        User user = repository.findUsersByEmail("ak_employee_test0@docusketch.com");
        //assertEquals(result.getResponse().getContentAsString(), user.toString());

        assertEquals(result.getResponse().getContentAsString(),
                "{\"_id\":{\"timestamp\":1645007133,\"date\":\"2022-02-16T10:25:33.000+00:00\"}," +
                "\"email\":\"ak_employee_test0@docusketch.com\",\"" +
                "hierarchyLevel\":\"OFFICE\",\"" +
                "parentID\":{\"timestamp\":1644826433,\"date\":\"2022-02-14T08:13:53.000+00:00\"}}");
        assertEquals(result.getClass(), user.getClass());
    }

    @Test
    void findUserID() throws Exception {
        mvc.perform(get("/users/idByEmail/alexander.kondratiev@docusketch.com"))
                .andExpect(content().string("620a0f41f4c7394dea52f102"));
    }

    @Test
    void findOffices() throws Exception {
        mvc.perform(get("/users/alexander.kondratiev@docusketch.com/offices"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
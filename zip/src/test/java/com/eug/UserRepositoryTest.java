package com.eug;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void addUser() {
        User user = new User();
        ObjectId userId = new ObjectId();
        user.set_id(userId);
        user.setEmail("test@gmail.com");
        user.setHierarchyLevel("OFFICE");
        //ParentID???

//        assertEquals(Object expected, Object actual)
//        Asserts that two objects are equal.
        assertEquals(user, userRepository.save(user));
    }

//    assertThat(T actual, Matcher<T> matcher)
//    Asserts that actual satisfies the condition specified by matcher.
    @Test
    public void shouldBeNotEmpty() {
        assertThat(userRepository.findAll()).isNotEmpty();
    }

}

//Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

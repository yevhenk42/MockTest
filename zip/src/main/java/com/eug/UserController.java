package com.eug;

import com.eug.User;
import com.eug.UserRepository;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping("/home")
    public String home(){
        return "User Repository Home Page";
    }

    @PostMapping("/users")
    public void addUsers(@RequestBody final List<User> users){
        repository.saveAll(users);
    }

    @GetMapping("/users")
    public List<User> findUsers(){
       return repository.findAll();
    }

    //Find user by email
    @GetMapping("/users/{email}")
    public User findUser(@PathVariable String email){
        return repository.findUsersByEmail(email);
    }

    // Find String id by email
    @GetMapping("/users/idByEmail/{email}")
    public String findUserID(@PathVariable String email){
        return repository.findUsersByEmail(email).get_id().toString();
    }

    @GetMapping("/users/{email}/offices")
    public String findOffices(@PathVariable String email){
        ObjectId ownerID = repository.findUsersByEmail(email).get_id();
        return repository.findUserByParentID(ownerID).toString();
    }

    @GetMapping("/users/{email}/employees")
    public List<User> findEmployees(@PathVariable String email){
        ObjectId ownerID = repository.findUsersByEmail(email).get_id();
        List<User> offices = repository.findUserByParentID(ownerID);
        List<ObjectId> officeIDs= new ArrayList<>();
        for (User user : offices){
                officeIDs.add(user.get_id());
        }
        System.out.println(officeIDs);
        return repository.findUserByParentIDIn(officeIDs);
    }

}

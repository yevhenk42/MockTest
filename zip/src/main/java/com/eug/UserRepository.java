package com.eug;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

@org.springframework.stereotype.Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    public User findUsersByEmail(String email);

    public List<User> findUserByParentID(ObjectId id);

    public List<User> findUserByParentIDIn(List<ObjectId> id);
}

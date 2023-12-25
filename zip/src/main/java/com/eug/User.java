package com.eug;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document
public class User{

        @Id
        private ObjectId _id;
        private String email;
        private String hierarchyLevel;

        private ObjectId parentID;


        // Add more fields as needed

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHierarchyLevel() {
        return hierarchyLevel;
    }

    public void setHierarchyLevel(String hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }

    public ObjectId getParentID() {
        return parentID;
    }

    public void setParentID(ObjectId parentID) {
        this.parentID = parentID;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", email='" + email + '\'' +
                ", hierarchyLevel='" + hierarchyLevel + '\'' +
                ", parentID=" + parentID +
                '}';
    }

}

package com.example.backend.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.Credentials.User;
@Repository("user")
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByMail(String mail);
}

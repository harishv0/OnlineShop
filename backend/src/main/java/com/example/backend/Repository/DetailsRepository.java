package com.example.backend.Repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.Credentials.Details;
@Repository("details")
public interface DetailsRepository extends MongoRepository<Details, ObjectId> {
    Details findByName(String name);
    /*Details findByNameList(List<String> name);*/
} 
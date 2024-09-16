package com.example.backend.Credentials;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "details")
public class Details{
    @Id
    private ObjectId id;
    private String type;
    private String name;
    private Double price;
    private String description;
    private String images;
    private List<String> properties;
}
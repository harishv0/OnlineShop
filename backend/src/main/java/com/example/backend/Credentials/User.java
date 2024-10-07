package com.example.backend.Credentials;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "shop")
@ToString
public class User {
    @Id
    private ObjectId id;
    private String name;
    private String mail;
    private String password;
    private String roles;
    private String profile;
    private List<String> carts;
}

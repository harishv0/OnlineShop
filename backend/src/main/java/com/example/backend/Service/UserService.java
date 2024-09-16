package com.example.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.backend.Credentials.User;
import com.example.backend.Repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private void UserRepo(@Qualifier("user") UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    public void save(User user) {
        userRepo.save(user);
    }
    public User getUser(String mail){
        return userRepo.findByMail(mail);
    }
    
    
}

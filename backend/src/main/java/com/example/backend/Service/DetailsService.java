package com.example.backend.Service;

import java.util.List;
import java.util.Base64;

import org.bson.codecs.Decoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.backend.Credentials.Details;
import com.example.backend.Repository.DetailsRepository;

import jakarta.websocket.Encoder;

@Service
public class DetailsService {
    @Autowired
    private DetailsRepository detailsRepo;
    
    public DetailsService(@Qualifier("details") DetailsRepository detailsRepo) {
        this.detailsRepo = detailsRepo;
    }

    //
    public List<Details> allDetails(){
        return detailsRepo.findAll();
    }

    public Details getDetails(String name){
        return detailsRepo.findByName(name);
    }

    /*public Details getDetailsList(String name) {
        return detailsRepo.findByNameList(name);
    } */

}

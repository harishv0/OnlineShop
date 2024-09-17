package com.example.backend.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.backend.ApiResponse.ApiResponse;
import com.example.backend.Credentials.Details;
import com.example.backend.Repository.DetailsRepository;
import com.example.backend.Service.DetailsService;
import com.example.backend.Storage.CloudinaryServices;

import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/details")
@CrossOrigin(origins = "https://harishcart.netlify.app")
public class DetailsController {

    @Autowired
    private DetailsService detailsService;

    @Autowired
    private DetailsRepository detailsRepo;

    @Autowired
    private CloudinaryServices cloudinaryServices;
    
    private ApiResponse<Details> response;

    @GetMapping("/allDetails")
    public ResponseEntity<ApiResponse<List<Details>>> allDetails(){
        try {
            List<Details> details = detailsService.allDetails();
            ApiResponse<List<Details>> response = new ApiResponse<List<Details>>(true, "Fetch All Details", details);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<Details>> response = new ApiResponse<List<Details>>(false, "Error", null);
            return ResponseEntity.badRequest().body(response);
        }
        
    }

    @GetMapping("/phoneDetails")
    public ResponseEntity<ApiResponse<Details>> details(@RequestParam("name") String name){
        try {
            Details details = detailsService.getDetails(name);
            ApiResponse<Details> response = new ApiResponse<Details>(true, "Fetched", details);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Details> response = new ApiResponse<Details>(false, "Error", null);
            return ResponseEntity.badRequest().body(response);
        }
        
    }

    @PostMapping("/uploadPost")
    public ResponseEntity<ApiResponse<Details>> uploadSec(@RequestParam("file") MultipartFile file, @RequestParam("type") String type, @RequestParam("name") String name,
                                                        @RequestParam("price") double price, @RequestParam("description") String description,
                                                        @RequestParam("properties") String properties){
            try{

                List<String> propertiesList = Arrays.asList(properties.split("\\s*,\\s*"));

                Details details = new Details();
                String folderName = name;

                String postUrl = cloudinaryServices.uploadPost(file, folderName, UUID.randomUUID().toString());
                details.setType(type);
                details.setName(name);
                details.setPrice(price);
                details.setImages(postUrl);
                details.setDescription(description);
                details.setProperties(propertiesList);
                
                detailsRepo.save(details);

                response = new ApiResponse<Details>(true, "Uploaded SuccessFully", details);
                return ResponseEntity.ok(response);
            }
            catch(Exception e){
                response = new ApiResponse<Details>(false, "Check your fields", null);
                return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).body(response);
            }
        }

       /*  @GetMapping("/phoneDetailsList")
    public ResponseEntity<ApiResponse<Details>> detailsList(@RequestParam(value = "name") List<String> name){
        try {
            Details details = detailsService.getDetailsList(name);
            if(name != null){
                response = new ApiResponse<Details>(true, "Fetched", details);
                return ResponseEntity.ok(response);
            }else{
                response = new ApiResponse<Details>(false, "Name not found", null);
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            ApiResponse<Details> response = new ApiResponse<Details>(false, "Error", null);
            return ResponseEntity.badRequest().body(response);
        }
        
    }*/

}

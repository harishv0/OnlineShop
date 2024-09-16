package com.example.backend.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.ApiResponse.ApiResponse;
import com.example.backend.Credentials.Details;
import com.example.backend.Credentials.User;
import com.example.backend.Service.DetailsService;
import com.example.backend.Service.UserService;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "https://harishcart.netlify.app")
public class UserController {
    
    @Autowired
    private UserService userService;

    private ApiResponse<User> response;

    @Autowired
    private DetailsService detailsService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User body){
        try {
            System.out.println(body.getName());
            body.setRoles("user");
            body.setCarts(new ArrayList<>());
            userService.save(body);
            return ResponseEntity.ok("Signup Successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PostMapping("/login")     
    public ResponseEntity<ApiResponse<User>> login(@RequestBody User body){
        User users = userService.getUser(body.getMail());
        System.out.println("Mail: " + users);
        if(users != null){
            if(users.getPassword().equals(body.getPassword())){
                response = new ApiResponse<>(true,"Login Success",users);
                return ResponseEntity.ok(response);
            }
            else{
                response = new ApiResponse<>(false, "Wrong Password", null);
                return ResponseEntity.badRequest().body(response);
            }
            
        }
        else{
            response = new ApiResponse<>(false, "Invalid Email and Password", null);
            return ResponseEntity.badRequest().body(response);
        }
    }
   
    @GetMapping("/profile")
    public ResponseEntity<User> getUserData(@RequestParam("mail") String mail){
        System.out.println(mail);
        User user = userService.getUser(mail);
        if(user != null){
            return ResponseEntity.ok(user);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addCart")
    public ResponseEntity<ApiResponse<User>> addCart(@RequestParam("mail") String mail, @RequestParam("product") String product ){
        System.out.println("Mail"+ mail);
        System.out.println("Product"+ product);
        User user = userService.getUser(mail);
        if(user != null){
            user.getCarts().add(product);
            userService.save(user);
            response = new ApiResponse<User>(true, "Added", user);
            return ResponseEntity.ok(response);
        }
        else{
            response = new ApiResponse<User>(false, "Not added", null);
            return ResponseEntity.badRequest().body(response);
        }
    }


    @GetMapping("/showCart")
    public ResponseEntity<ApiResponse<List<Details>>> retrieveCart(@RequestParam("mail") String mail){
        User user = userService.getUser(mail);
        List<Details> details = new ArrayList<>();
        if(user != null){
            for (String detail : user.getCarts()) {
                Details product = detailsService.getDetails(detail);
                if(!details.contains(product)) details.add(product);
            }
            ApiResponse<List<Details>> response = new ApiResponse<List<Details>>(true, "Reterived", details);
            return ResponseEntity.ok(response);
        }
        else{
            ApiResponse<List<Details>> response = new ApiResponse<List<Details>>(false, "Not found", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/removecartitem")
    public ResponseEntity<ApiResponse<List<Details>>> removeCartItem(@RequestParam("mail") String mail, @RequestParam("item") String item) {
        User user = userService.getUser(mail);
        if(user != null){
            if(user.getCarts().contains(item)){
                List<String> items = user.getCarts();
                int itemIndex = -1;
                for (int i = 0; i < items.size(); i++) {
                    if(items.get(i).equals(item)) itemIndex = i;
                }

                if(itemIndex > -1){
                    items.remove(itemIndex);
                }

                user.setCarts(items);
                userService.save(user);
            }
            ApiResponse<List<Details>> response = new ApiResponse<List<Details>>(true, "Reterived", null);
            return ResponseEntity.ok(response);
        }
        else{
            ApiResponse<List<Details>> response = new ApiResponse<List<Details>>(false, "Not found", null);
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    

}

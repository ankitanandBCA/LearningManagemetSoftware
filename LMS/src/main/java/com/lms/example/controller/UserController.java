package com.lms.example.controller;

import com.lms.example.entity.User;
import com.lms.example.repositry.UserRepo;
import com.lms.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @PostMapping("/AddUsers")
    @CrossOrigin(origins = "https://lmssoftware-7c720.web.app")
    public User register(@RequestBody User user)
    {
        return userService.adduser(user);
    }


    @PostMapping("/loginUser")
    @CrossOrigin(origins = "https://lmssoftware-7c720.web.app")
    public ResponseEntity<?> login(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();

        User loginuser = userService.loginuser(email, password);
        if (loginuser != null) {
            // Hardcoded admin check
            if ("ankitanandraj9563@gmail.com".equalsIgnoreCase(email) && "adminankit9563@".equals(password)) {
                return ResponseEntity.ok("admin");
            } else {
                return ResponseEntity.ok("user");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
        }
    }

    @GetMapping("/AllUser")
    @CrossOrigin(origins = "https://lmssoftware-7c720.web.app")
    public List<User> getallUser()
    {
        return userRepo.findAll();
    }

    @DeleteMapping("/deleteUser/{id}")
    @CrossOrigin(origins = "https://lmssoftware-7c720.web.app")
    public Boolean deleteuser(@PathVariable int id)
    {
        userRepo.deleteById(id);
        return true;
    }




    @GetMapping("/totaluser")
    @CrossOrigin(origins = "https://lmssoftware-7c720.web.app")
    public long totalusersss()
    {
        return userRepo.count();
    }






}

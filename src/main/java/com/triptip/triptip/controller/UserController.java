package com.triptip.triptip.controller;

import com.triptip.triptip.model.Address;
import com.triptip.triptip.model.User;
import com.triptip.triptip.repository.UserRepository;
import com.triptip.triptip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin ("http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {

        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        System.out.print((encodedPassword));
        return userService.addUser(user);

    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String token = "token -" + user.getLogin();
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());

        User storedUser = userRepository.findByLogin(user.getLogin()); // Assuming you have a method like this in your repository

        if (storedUser == null || !passwordEncoder.matches(user.getPassword(), storedUser.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid login credentials");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", storedUser);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @CrossOrigin ("http://localhost:3000")
    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/admin/getUsers")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @DeleteMapping("/admin/deleteUser/{id}")
    public void deleteUser(@PathVariable  int id){
        User user = userRepository.findById(id);
        if(user==null)
            System.out.println("User not found");
        else
            userRepository.delete(user);
    }

    @PutMapping("/editUser/{id}")
    public ResponseEntity<User> editUser(@PathVariable int id,@RequestBody User user){
        userService.editDetails(id,user.getFname(),user.getLname(),user.getEmail(), user.getPhone());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/editUserAddress/{userId}")
    public ResponseEntity<User> editAddress(@PathVariable int userId, @RequestBody Address address) {
        userService.editUserAddress2(userId, address);
        System.out.println("xd");
        return ResponseEntity.ok().build();
    }
}

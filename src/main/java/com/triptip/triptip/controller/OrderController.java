package com.triptip.triptip.controller;


import com.triptip.triptip.service.OrderService;
import com.triptip.triptip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"http://localhost:3000", "http://localhost:4000"})
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping ("/addOrder") // Change to GET mapping
    public ResponseEntity<String> addOrder(@RequestParam("login") String login) {
        try {
            orderService.addOrder(login);
            return new ResponseEntity<>("Order added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

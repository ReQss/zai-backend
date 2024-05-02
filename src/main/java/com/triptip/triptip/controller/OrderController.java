package com.triptip.triptip.controller;


import com.triptip.triptip.model.Order;
import com.triptip.triptip.model.OrderItem;
import com.triptip.triptip.model.Product;
import com.triptip.triptip.model.User;
import com.triptip.triptip.repository.ProductRepository;
import com.triptip.triptip.repository.UserRepository;
import com.triptip.triptip.service.OrderItemService;
import com.triptip.triptip.service.OrderService;
import com.triptip.triptip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin({"http://localhost:3000", "http://localhost:4000"})
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @GetMapping ("/addOrder") // Change to GET mapping
    public ResponseEntity<String> addOrder(@RequestParam("login") String login, @RequestParam("totalPrice") int totalPrice) {
        try {
            User finded_user = userRepository.findByLogin(login);
            Order order = new Order(finded_user, LocalDate.now(), (float)totalPrice/100, finded_user.getAddress());
            orderService.addOrder(order);
            return new ResponseEntity<>("Order added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}

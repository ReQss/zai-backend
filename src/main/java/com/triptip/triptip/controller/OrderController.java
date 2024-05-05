package com.triptip.triptip.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.triptip.triptip.model.*;
import com.triptip.triptip.repository.OrderItemRepository;
import com.triptip.triptip.repository.OrderRepository;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @GetMapping("/addOrder")
    public ResponseEntity<String> addOrder(@RequestParam("login") String login, @RequestParam("totalPrice") int totalPrice, @RequestParam("items") String items) {
        try {
            JsonNode itemsNode = objectMapper.readTree(items);

            List<Map<String, Object>> itemDataList = new ArrayList<>();

            for (JsonNode item : itemsNode) {
                long id = item.get("id").asLong();
                int quantity = item.get("quantity").asInt();
                Map<String, Object> itemData = new HashMap<>();
                itemData.put("id", id);
                itemData.put("quantity", quantity);
                itemDataList.add(itemData);
            }

            for (Map<String, Object> itemData : itemDataList) {
                System.out.println("ID: " + itemData.get("id") + ", Quantity: " + itemData.get("quantity"));
            }


            User finded_user = userRepository.findByLogin(login);
            Order order = new Order(finded_user, LocalDate.now(), (float)totalPrice/100, finded_user.getAddress());
            orderService.addOrder(order);
            orderItemService.addItems(itemDataList,order.getOrderId());
            return new ResponseEntity<>("Order added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/orders")
    public List<OrderDTO> getOrdersWithItems(@RequestParam("userId") int userId) {
        User findedUser = userRepository.findById(userId);
        List<Order> orders = orderRepository.findAllByUser(findedUser);
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(order.getOrderId());
            OrderDTO orderDTO = new OrderDTO(order, orderItems);
            orderDTOs.add(orderDTO);
        }
        return orderDTOs;
    }

}

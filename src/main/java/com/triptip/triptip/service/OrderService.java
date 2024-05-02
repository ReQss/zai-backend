package com.triptip.triptip.service;

import com.triptip.triptip.model.Order;
import com.triptip.triptip.model.User;
import com.triptip.triptip.repository.OrderRepository;
import com.triptip.triptip.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

//    public void addOrder(User finded_user){
//        User user = finded_user;
//        Order order = new Order(user, LocalDate.now(),100,user.getAddress());
//        orderRepository.save(order);
//    }
    public void addOrder(Order order){
        orderRepository.save(order);
    }
}


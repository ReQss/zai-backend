package com.triptip.triptip.service;

import com.triptip.triptip.model.OrderItem;
import com.triptip.triptip.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;
    public void addOrderItems(List <OrderItem> orderItems){
        System.out.println(orderItems);
        orderItemRepository.saveAll(orderItems);
    }
}

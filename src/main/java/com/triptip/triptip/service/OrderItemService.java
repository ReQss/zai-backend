package com.triptip.triptip.service;

import com.triptip.triptip.model.OrderItem;
import com.triptip.triptip.repository.OrderItemRepository;
import com.triptip.triptip.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    ProductRepository productRepository;

    public void addItems(List<Map<String, Object>> itemDataList, int orderId){
        for (Map<String, Object> itemData : itemDataList) {
            long itemId = (long) itemData.get("id");
            int itemQuantity = (int) itemData.get("quantity");
            OrderItem orderItem = new OrderItem(orderId,productRepository.findById((int)itemId),itemQuantity);
            orderItemRepository.save(orderItem);
            System.out.println("ID: " + itemData.get("id") + ", Quantity: " + itemData.get("quantity"));
        }
    }

}

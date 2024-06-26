package com.triptip.triptip.repository;

import com.triptip.triptip.model.Order;
import com.triptip.triptip.model.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByOrderId(int id);
    List<Order> findAllByUser(User user);
    List<Order> findAll();
}

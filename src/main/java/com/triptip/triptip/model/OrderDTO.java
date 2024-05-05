package com.triptip.triptip.model;

import java.util.List;

public class OrderDTO {
    private Order order;
    private List<OrderItem> orderItems;
    public OrderDTO(Order order, List<OrderItem> orderItems) {
        this.order = order;
        this.orderItems = orderItems;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "order=" + order +
                ", orderItems=" + orderItems +
                '}';
    }
}

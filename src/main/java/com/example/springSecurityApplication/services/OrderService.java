package com.example.springSecurityApplication.services;


import com.example.springSecurityApplication.enumm.Status;
import com.example.springSecurityApplication.models.Order;

import com.example.springSecurityApplication.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getById(int id){
        Optional<Order> order_db = orderRepository.findById(id);
        return order_db.orElse(null);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Status> getAllStatus(){
        List <Status> statusList = List.of(Status.values());
        return statusList;
    }

    @Transactional
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    public void updateOrder(Order order){
        orderRepository.save(order);
    }
}

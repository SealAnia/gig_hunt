package com.example.gig_hunt.service.impl;

import com.example.gig_hunt.model.entity.OrderDetails;
import com.example.gig_hunt.model.entity.OrderStatus;
import com.example.gig_hunt.model.repository.OrderDetailsRepository;
import com.example.gig_hunt.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public List<OrderDetails> getAll() {
        return orderDetailsRepository.findAll();
    }

    @Override
    public OrderDetails readById(Long id) {
        return orderDetailsRepository.findById(id).get();
    }

    @Override
    public OrderDetails createOrUpdate(OrderDetails orderDetails) {
        orderDetails.setStatus(OrderStatus.WAITING_FOR_REPLY);
        if(orderDetails.getQuantity() == 0) {
            orderDetails.setQuantity(1);
        }
        return orderDetailsRepository.saveAndFlush(orderDetails);
    }

    @Override
    public void delete(Long id) {
        orderDetailsRepository.deleteById(id);
    }

    @Override
    public List<OrderDetails> getOrdersOfMaster(Long userId) {
        return orderDetailsRepository.getOrdersOfMaster(userId);
    }

    @Override
    public OrderDetails acceptOrder(OrderDetails orderDetails) {
        if(orderDetails.getStatus().equals(OrderStatus.WAITING_FOR_REPLY)) {
            orderDetails.setDate(orderDetails.getDate());
            orderDetails.setCustomer(orderDetails.getCustomer());
            orderDetails.setGoods(orderDetails.getGoods());
            orderDetails.setQuantity(orderDetails.getQuantity());
            orderDetails.setStatus(OrderStatus.IN_PROGRESS);

            //ADD FIELD ACTIVE_ORDERS IN MASTER

        }
        return orderDetailsRepository.saveAndFlush(orderDetails);
    }

}

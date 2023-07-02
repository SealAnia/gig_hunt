package com.example.gig_hunt.controller;

import com.example.gig_hunt.model.entity.OrderDetails;
import com.example.gig_hunt.service.impl.OrderDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderDetailsRestController {

    private final OrderDetailsServiceImpl orderDetailsService;

    @Autowired
    public OrderDetailsRestController(OrderDetailsServiceImpl orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping(value = "")
    public List<OrderDetails> getAllOrders() {
        return orderDetailsService.getAll();
    }

    @GetMapping(value = "/{orderId}")
    public OrderDetails getOrderById(@PathVariable Long orderId) {
        return orderDetailsService.readById(orderId);
    }

    //RETURNS ALL ORDERS FROM A CERTAIN MASTER
    @GetMapping(value = "/of_master")
    public List<OrderDetails> getOrdersOfMaster(@RequestParam(value = "user_id") Long userId) {
        return orderDetailsService.getOrdersOfMaster(userId);
    }

    @PostMapping(value = "/")
    public void addOrderDetails(@RequestBody OrderDetails order) {
        orderDetailsService.createOrUpdate(order);
    }

    @PutMapping(value = "/{orderId}")
    public ResponseEntity<OrderDetails> updateOrderDetails(@PathVariable Long orderId, @RequestBody OrderDetails order) {
        OrderDetails updatedOrder = orderDetailsService.readById(orderId);
        updatedOrder.setDate(order.getDate());
        updatedOrder.setGoods(order.getGoods());
        updatedOrder.setCustomer(order.getCustomer());
        updatedOrder.setQuantity(order.getQuantity());
        orderDetailsService.createOrUpdate(updatedOrder);
        return ResponseEntity.ok(updatedOrder);
    }

    //NEW
    @PutMapping(value = "/{orderId}/accept")
    public ResponseEntity<OrderDetails> acceptOrder(@PathVariable Long orderId, @RequestBody OrderDetails order) {
        OrderDetails orderToAccept = orderDetailsService.readById(orderId);
        orderDetailsService.acceptOrder(orderToAccept);
        return ResponseEntity.ok(orderToAccept);
    }

    @DeleteMapping(value = "/{orderId}")
    public void deleteOrderDetails(@PathVariable Long orderId) {
        orderDetailsService.delete(orderId);
    }

}

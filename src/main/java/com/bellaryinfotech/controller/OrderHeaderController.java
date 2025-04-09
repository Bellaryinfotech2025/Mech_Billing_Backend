package com.bellaryinfotech.controller;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bellaryinfotech.model.OrderHeader;
import com.bellaryinfotech.security.OrderHeaderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*") // Enable CORS for all origins
public class OrderHeaderController {

    @Autowired
    private OrderHeaderService orderHeaderService;
    
    @GetMapping
    public ResponseEntity<List<OrderHeader>> getAllOrders() {
        List<OrderHeader> orders = orderHeaderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<OrderHeader> getOrderById(@PathVariable Long id) {
        Optional<OrderHeader> order = orderHeaderService.getOrderById(id);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping
    public ResponseEntity<OrderHeader> createOrder(@RequestBody OrderHeader orderHeader) {
        OrderHeader savedOrder = orderHeaderService.saveOrder(orderHeader);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<OrderHeader> updateOrder(@PathVariable Long id, @RequestBody OrderHeader orderHeader) {
        if (!orderHeaderService.getOrderById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        orderHeader.setOrderId(id);
        OrderHeader updatedOrder = orderHeaderService.saveOrder(orderHeader);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (!orderHeaderService.getOrderById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        orderHeaderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


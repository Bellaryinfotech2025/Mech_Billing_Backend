package com.bellaryinfotech.controller;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bellaryinfotech.model.OrderDetailsHeader;
import com.bellaryinfotech.repo.OrderDetailsHeaderRepository;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class OrderDatabaseSearchController {
    
    @Autowired
    private OrderDetailsHeaderRepository orderHeaderRepository;
    
    @GetMapping("/orderscontroller")
    public ResponseEntity<List<OrderDetailsHeader>> getAllOrders() {
        List<OrderDetailsHeader> orders = orderHeaderRepository.findAll();
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/ordersearchapi")
    public ResponseEntity<List<OrderDetailsHeader>> searchOrders(@RequestParam String query) {
         
        List<OrderDetailsHeader> orders = orderHeaderRepository.findAll();
        return ResponseEntity.ok(orders);
    }
    
    @PutMapping("/update-order")
    public ResponseEntity<Map<String, Object>> updateOrder(@RequestBody OrderDetailsHeader order) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Check karta ordr hai ki nai
            OrderDetailsHeader existingOrder = orderHeaderRepository.findById(order.getOrderId())
                .orElse(null);
                
            if (existingOrder == null) {
                response.put("status", "error");
                response.put("message", "Order not found");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Update the order
            OrderDetailsHeader updatedOrder = orderHeaderRepository.save(order);
            
            response.put("status", "success");
            response.put("message", "Order updated successfully");
            response.put("data", updatedOrder);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to update order: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
}

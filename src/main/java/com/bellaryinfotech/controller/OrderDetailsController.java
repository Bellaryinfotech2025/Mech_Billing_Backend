package com.bellaryinfotech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bellaryinfotech.model.OrderDetailsHeader;
import com.bellaryinfotech.model.OrderDetailsLookup;
import com.bellaryinfotech.service.OrderDetailsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class OrderDetailsController {
    
    @Autowired
    private OrderDetailsService orderDetailsService;
    
    @GetMapping("/order-lookup-values")  // Changed from "/lookup-values"
    public ResponseEntity<Map<String, List<OrderDetailsLookup>>> getAllLookupValues() {
        // Get all lookup values from the service
        Map<String, List<OrderDetailsLookup>> lookupValues = orderDetailsService.getAllLookupValues();
        return ResponseEntity.ok(lookupValues);
    }
    
    @PostMapping("/post-orders")
    public ResponseEntity<Map<String, Object>> saveOrder(@RequestBody OrderDetailsHeader orderData) {
        try {
            // Save the order data
            Map<String, Object> result = orderDetailsService.saveOrder(orderData);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("status", "error");
            errorResult.put("message", "Failed to save order: " + e.getMessage());
            return ResponseEntity.ok(errorResult);
        }
    }
}
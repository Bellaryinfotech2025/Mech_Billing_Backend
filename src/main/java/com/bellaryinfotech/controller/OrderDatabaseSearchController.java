package com.bellaryinfotech.controller;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bellaryinfotech.model.OrderDetailsHeader;
import com.bellaryinfotech.repo.OrderDetailsHeaderRepository;

import java.util.List;

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
        // Implement search logic here
        // This is just a placeholder - you would need to implement the actual search
        List<OrderDetailsHeader> orders = orderHeaderRepository.findAll();
        return ResponseEntity.ok(orders);
    }
}


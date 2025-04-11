package com.bellaryinfotech.controller;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private OrderDetailsService billingService;
    
    @GetMapping("/billing-frequencies")
    public ResponseEntity<List<OrderDetailsLookup>> getBillingFrequencies() {
        // Get billing frequencies from the service
        List<OrderDetailsLookup> frequencies = billingService.getBillingFrequencies();
        return ResponseEntity.ok(frequencies);
    }
    
    @PostMapping("/billing-frequency")
    public ResponseEntity<Map<String, Object>> saveBillingFrequency(@RequestBody String billingFrequency) {
        try {
            // Trim any whitespace or quotes that might be in the request body
            String cleanedFrequency = billingFrequency.trim().replace("\"", "");
            
            // Save the billing frequency (lookup code) to the order header
            Map<String, Object> result = billingService.saveBillingFrequency(cleanedFrequency);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("status", "error");
            errorResult.put("message", "Failed to save billing frequency: " + e.getMessage());
            return ResponseEntity.ok(errorResult);
        }
    }
}
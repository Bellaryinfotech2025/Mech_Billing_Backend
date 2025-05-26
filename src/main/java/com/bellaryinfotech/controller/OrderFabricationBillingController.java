package com.bellaryinfotech.controller;

import com.bellaryinfotech.DTO.OrderFabricationBillingDTO;
import com.bellaryinfotech.service.OrderFabricationBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v3.0/billing")
public class OrderFabricationBillingController {

    @Autowired
    private OrderFabricationBillingService billingService;

     
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<OrderFabricationBillingDTO>> getAllBillings() {
        List<OrderFabricationBillingDTO> billings = billingService.getAllBillings();
        return ResponseEntity.ok(billings);
    }

     
    @PostMapping(value = "/store", produces = "application/json")
    public ResponseEntity<List<OrderFabricationBillingDTO>> storeSelectedErectionMkds(@RequestBody ErectionMkdRequest request) {
        if (request.getErectionMkds() == null || request.getErectionMkds().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<OrderFabricationBillingDTO> stored = billingService.storeSelectedErectionMkds(request.getErectionMkds());
        return ResponseEntity.ok(stored);
    }

    
    public static class ErectionMkdRequest {
        private List<String> erectionMkds;
        public List<String> getErectionMkds() { return erectionMkds; }
        public void setErectionMkds(List<String> erectionMkds) { this.erectionMkds = erectionMkds; }
    }
}

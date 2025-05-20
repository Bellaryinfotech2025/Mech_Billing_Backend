package com.bellaryinfotech.controller;

import com.bellaryinfotech.DTO.OrderFabricationDetailDTO;
import com.bellaryinfotech.service.OrderFabricationDetailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v3.0")
public class OrderFabricationDetailController {
    
    private final OrderFabricationDetailService orderFabricationDetailService;
    
    @Autowired
    public OrderFabricationDetailController(
            OrderFabricationDetailService orderFabricationDetailService) {
        this.orderFabricationDetailService = orderFabricationDetailService;
    }
    
    @GetMapping("/loadingfabricatiodetail")
    public ResponseEntity<List<OrderFabricationDetailDTO>> getAllOrderFabricationDetails() {
        List<OrderFabricationDetailDTO> orderFabricationDetails = orderFabricationDetailService.findAll();
        return ResponseEntity.ok(orderFabricationDetails);
    }
    
    @GetMapping("/loadingfabricatiodetail/{id}")
    public ResponseEntity<OrderFabricationDetailDTO> getOrderFabricationDetailById(@PathVariable Long id) {
        try {
            OrderFabricationDetailDTO orderFabricationDetail = orderFabricationDetailService.findById(id);
            return ResponseEntity.ok(orderFabricationDetail);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/sendfabricationdetails")
    public ResponseEntity<OrderFabricationDetailDTO> createOrderFabricationDetail(
            @RequestBody OrderFabricationDetailDTO orderFabricationDetailDTO) {
        OrderFabricationDetailDTO createdOrderFabricationDetail = orderFabricationDetailService.save(orderFabricationDetailDTO);
        return new ResponseEntity<>(createdOrderFabricationDetail, HttpStatus.CREATED);
    }
    
    @PutMapping("/updatefabricationdetails/{id}")
    public ResponseEntity<OrderFabricationDetailDTO> updateOrderFabricationDetail(
            @PathVariable Long id,
            @RequestBody OrderFabricationDetailDTO orderFabricationDetailDTO) {
        try {
            OrderFabricationDetailDTO updatedOrderFabricationDetail = orderFabricationDetailService.update(id, orderFabricationDetailDTO);
            return ResponseEntity.ok(updatedOrderFabricationDetail);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/deletefabricationdetails/{id}")
    public ResponseEntity<Void> deleteOrderFabricationDetail(@PathVariable Long id) {
        try {
            orderFabricationDetailService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/loadingfabricatiodetail/order/{orderId}")
    public ResponseEntity<List<OrderFabricationDetailDTO>> getOrderFabricationDetailsByOrderId(@PathVariable Long orderId) {
        List<OrderFabricationDetailDTO> orderFabricationDetails = orderFabricationDetailService.findByOrderId(orderId);
        return ResponseEntity.ok(orderFabricationDetails);
    }
    
    @GetMapping("/loadingfabricatiodetail/building/{buildingName}")
    public ResponseEntity<List<OrderFabricationDetailDTO>> getOrderFabricationDetailsByBuildingName(
            @PathVariable String buildingName) {
        List<OrderFabricationDetailDTO> orderFabricationDetails = orderFabricationDetailService.findByBuildingName(buildingName);
        return ResponseEntity.ok(orderFabricationDetails);
    }
    
    @GetMapping("/loadingfabricatiodetail/drawing/{drawingNo}")
    public ResponseEntity<List<OrderFabricationDetailDTO>> getOrderFabricationDetailsByDrawingNo(
            @PathVariable String drawingNo) {
        List<OrderFabricationDetailDTO> orderFabricationDetails = orderFabricationDetailService.findByDrawingNo(drawingNo);
        return ResponseEntity.ok(orderFabricationDetails);
    }
    
    @GetMapping("/loadingfabricatiodetail/order-number/{orderNumber}")
    public ResponseEntity<List<OrderFabricationDetailDTO>> getOrderFabricationDetailsByOrderNumber(
            @PathVariable String orderNumber) {
        List<OrderFabricationDetailDTO> orderFabricationDetails = orderFabricationDetailService.findByOrderNumber(orderNumber);
        return ResponseEntity.ok(orderFabricationDetails);
    }
    
    @GetMapping("/loadingfabricatiodetail/erection/{erectionMkd}")
    public ResponseEntity<List<OrderFabricationDetailDTO>> getOrderFabricationDetailsByErectionMkd(
            @PathVariable String erectionMkd) {
        List<OrderFabricationDetailDTO> orderFabricationDetails = orderFabricationDetailService.findByErectionMkd(erectionMkd);
        return ResponseEntity.ok(orderFabricationDetails);
    }
    
    @GetMapping("/loadingfabricatiodetail/line/{lineNumber}/erection/{erectionMkd}")
    public ResponseEntity<Map<String, Object>> getOrderFabricationDetailsByLineNumberAndErectionMkd(
            @PathVariable String lineNumber,
            @PathVariable String erectionMkd) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<OrderFabricationDetailDTO> fabricationDetails = 
                    orderFabricationDetailService.findByLineNumberAndErectionMkd(lineNumber, erectionMkd);
            
            response.put("status", "success");
            response.put("fabricationDetails", fabricationDetails);
            response.put("count", fabricationDetails.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/loadingfabricatiodetail/line-id/{lineId}/erection/{erectionMkd}")
    public ResponseEntity<Map<String, Object>> getOrderFabricationDetailsByLineIdAndErectionMkd(
            @PathVariable Long lineId,
            @PathVariable String erectionMkd) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<OrderFabricationDetailDTO> fabricationDetails = 
                    orderFabricationDetailService.findByLineIdAndErectionMkd(lineId, erectionMkd);
            
            response.put("status", "success");
            response.put("fabricationDetails", fabricationDetails);
            response.put("count", fabricationDetails.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
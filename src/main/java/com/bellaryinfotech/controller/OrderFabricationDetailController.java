package com.bellaryinfotech.controller;

import com.bellaryinfotech.DTO.OrderFabricationDetailDTO;
import com.bellaryinfotech.service.OrderFabricationDetailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3.0")
public class OrderFabricationDetailController {
    
    private final OrderFabricationDetailService orderFabricationDetailService;
    
    @Autowired
    public OrderFabricationDetailController(OrderFabricationDetailService orderFabricationDetailService) {
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
}

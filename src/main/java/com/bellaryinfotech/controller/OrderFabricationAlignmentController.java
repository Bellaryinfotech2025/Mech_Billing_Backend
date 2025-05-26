package com.bellaryinfotech.controller;

import com.bellaryinfotech.DTO.OrderFabricationAlignmentDTO;
import com.bellaryinfotech.service.OrderFabricationAlignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v3.0/alignment")
public class OrderFabricationAlignmentController {

    @Autowired
    private OrderFabricationAlignmentService alignmentService;

     
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<OrderFabricationAlignmentDTO>> getAllAlignments() {
        List<OrderFabricationAlignmentDTO> alignments = alignmentService.getAllAlignments();
        return ResponseEntity.ok(alignments);
    }

     
    @PostMapping(value = "/store", produces = "application/json")
    public ResponseEntity<List<OrderFabricationAlignmentDTO>> storeSelectedErectionMkds(@RequestBody ErectionMkdRequest request) {
        if (request.getErectionMkds() == null || request.getErectionMkds().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<OrderFabricationAlignmentDTO> stored = alignmentService.storeSelectedErectionMkds(request.getErectionMkds());
        return ResponseEntity.ok(stored);
    }

     
    public static class ErectionMkdRequest {
        private List<String> erectionMkds;
        public List<String> getErectionMkds() { return erectionMkds; }
        public void setErectionMkds(List<String> erectionMkds) { this.erectionMkds = erectionMkds; }
    }
}

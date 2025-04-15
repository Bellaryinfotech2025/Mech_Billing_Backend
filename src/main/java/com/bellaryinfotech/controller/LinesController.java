package com.bellaryinfotech.controller;

import com.bellaryinfotech.model.LinesModel;
import com.bellaryinfotech.service.LinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lines")
@CrossOrigin(origins = "*")
public class LinesController {
    
    @Autowired
    private LinesService linesService;
    
    @GetMapping("/fetchAllLines")
    public ResponseEntity<Map<String, Object>> fetchAllLines() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<LinesModel> lines = linesService.getAllOrderLines();
            response.put("status", "success");
            response.put("lines", lines);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to fetch lines: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/fetchLinesByOrder/{orderId}")
    public ResponseEntity<Map<String, Object>> fetchLinesByOrder(@PathVariable Long orderId) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<LinesModel> lines = linesService.getOrderLinesByOrderId(orderId);
            response.put("status", "success");
            response.put("lines", lines);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // Add this to print the full stack trace
            response.put("status", "error");
            response.put("message", "Failed to fetch lines for order " + orderId + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/fetchLineDetails/{lineId}")
    public ResponseEntity<Map<String, Object>> fetchLineDetails(@PathVariable Long lineId) {
        Map<String, Object> response = new HashMap<>();
        try {
            return linesService.getOrderLineById(lineId)
                    .map(line -> {
                        response.put("status", "success");
                        response.put("line", line);
                        return ResponseEntity.ok(response);
                    })
                    .orElseGet(() -> {
                        response.put("status", "error");
                        response.put("message", "Line not found with id: " + lineId);
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                    });
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to fetch line details: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/createParentLine")
    public ResponseEntity<Map<String, Object>> createParentLine(@RequestBody LinesModel line) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Ensure it's marked as a parent line
            line.setIsParent(true);
        
            LinesModel savedLine = linesService.saveOrderLine(line);
            response.put("status", "success");
            response.put("message", "Parent line created successfully");
            response.put("lineId", savedLine.getLineId());
            response.put("lineNumber", savedLine.getLineNumber());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace(); // Print the full stack trace
            response.put("status", "error");
            response.put("message", "Failed to create parent line: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/createChildLine")
    public ResponseEntity<Map<String, Object>> createChildLine(@RequestBody LinesModel line) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Ensure it's marked as a child line
            line.setIsParent(false);
        
            // Validate parent line number is provided
            if (line.getParentLineNumber() == null) {
                response.put("status", "error");
                response.put("message", "Parent line number is required for child lines");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        
            LinesModel savedLine = linesService.saveOrderLine(line);
            response.put("status", "success");
            response.put("message", "Child line created successfully");
            response.put("lineId", savedLine.getLineId());
            response.put("lineNumber", savedLine.getLineNumber());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace(); // Print the full stack trace
            response.put("status", "error");
            response.put("message", "Failed to create child line: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PutMapping("/updateLine/{lineId}")
    public ResponseEntity<Map<String, Object>> updateLine(
            @PathVariable Long lineId,
            @RequestBody LinesModel line) {
        Map<String, Object> response = new HashMap<>();
        try {
            LinesModel updatedLine = linesService.updateOrderLine(lineId, line);
            response.put("status", "success");
            response.put("message", "Line updated successfully");
            response.put("line", updatedLine);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to update line: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @DeleteMapping("/deleteLine/{lineId}")
    public ResponseEntity<Map<String, Object>> deleteLine(@PathVariable Long lineId) {
        Map<String, Object> response = new HashMap<>();
        try {
            linesService.deleteOrderLine(lineId);
            response.put("status", "success");
            response.put("message", "Line deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to delete line: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/fetchChildLines/{parentLineNumber}")
    public ResponseEntity<Map<String, Object>> fetchChildLines(@PathVariable Integer parentLineNumber) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<LinesModel> childLines = linesService.getChildLines(parentLineNumber);
            response.put("status", "success");
            response.put("childLines", childLines);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to fetch child lines: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

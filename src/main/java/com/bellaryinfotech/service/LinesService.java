package com.bellaryinfotech.service;

import com.bellaryinfotech.model.LinesModel;
import com.bellaryinfotech.repo.LinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LinesService {
    
    @Autowired
    private LinesRepository linesRepository;
    
    public List<LinesModel> getAllOrderLines() {
        return linesRepository.findAll();
    }
    
    public List<LinesModel> getOrderLinesByOrderId(Long orderId) {
        return linesRepository.findByOrderId(orderId);
    }
    
    public Optional<LinesModel> getOrderLineById(Long lineId) {
        return linesRepository.findById(lineId);
    }
    
    public LinesModel saveOrderLine(LinesModel orderLine) {
        // If it's a new line, generate a line number
        if (orderLine.getLineId() == null) {
            Integer maxLineNumber = linesRepository.findMaxLineNumberByOrderId(orderLine.getOrderId());
            orderLine.setLineNumber(maxLineNumber != null ? maxLineNumber + 1 : 1);
        }
        
        // Set default status if not provided
        if (orderLine.getStatus() == null) {
            orderLine.setStatus("ACTIVE");
        }
        
        // Don't use isParent field in database operations
        // The isParent field is now transient and won't be persisted
        
        return linesRepository.save(orderLine);
    }
    
    public Map<String, Object> saveOrderLineWithResponse(LinesModel orderLine) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            LinesModel savedLine = saveOrderLine(orderLine);
            response.put("status", "success");
            response.put("message", "Order line saved successfully");
            response.put("lineId", savedLine.getLineId());
            response.put("lineNumber", savedLine.getLineNumber());
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to save order line: " + e.getMessage());
        }
        
        return response;
    }
    
    public List<LinesModel> getChildLines(Integer parentLineNumber) {
        return linesRepository.findByParentLineNumber(parentLineNumber);
    }
    
    public void deleteOrderLine(Long lineId) {
        linesRepository.deleteById(lineId);
    }
    
    public LinesModel updateOrderLine(Long lineId, LinesModel orderLineDetails) {
        LinesModel orderLine = linesRepository.findById(lineId)
                .orElseThrow(() -> new RuntimeException("Order line not found with id: " + lineId));
        
        // Update fields
        if (orderLineDetails.getServiceName() != null) {
            orderLine.setServiceName(orderLineDetails.getServiceName());
        }
        if (orderLineDetails.getItemDescription() != null) {
            orderLine.setItemDescription(orderLineDetails.getItemDescription());
        }
        if (orderLineDetails.getEffectiveStartDate() != null) {
            orderLine.setEffectiveStartDate(orderLineDetails.getEffectiveStartDate());
        }
        if (orderLineDetails.getEffectiveEndDate() != null) {
            orderLine.setEffectiveEndDate(orderLineDetails.getEffectiveEndDate());
        }
        if (orderLineDetails.getUom() != null) {
            orderLine.setUom(orderLineDetails.getUom());
        }
        if (orderLineDetails.getOrderedQuantity() != null) {
            orderLine.setOrderedQuantity(orderLineDetails.getOrderedQuantity());
        }
        if (orderLineDetails.getBillingQuantity() != null) {
            orderLine.setBillingQuantity(orderLineDetails.getBillingQuantity());
        }
        if (orderLineDetails.getUnitPrice() != null) {
            orderLine.setUnitPrice(orderLineDetails.getUnitPrice());
        }
        if (orderLineDetails.getTotalPrice() != null) {
            orderLine.setTotalPrice(orderLineDetails.getTotalPrice());
        }
        if (orderLineDetails.getBillingFrequency() != null) {
            orderLine.setBillingFrequency(orderLineDetails.getBillingFrequency());
        }
        if (orderLineDetails.getBillingCycle() != null) {
            orderLine.setBillingCycle(orderLineDetails.getBillingCycle());
        }
        if (orderLineDetails.getBillToCustomerId() != null) {
            orderLine.setBillToCustomerId(orderLineDetails.getBillToCustomerId());
        }
        if (orderLineDetails.getBillToSiteId() != null) {
            orderLine.setBillToSiteId(orderLineDetails.getBillToSiteId());
        }
        if (orderLineDetails.getBillToContactId() != null) {
            orderLine.setBillToContactId(orderLineDetails.getBillToContactId());
        }
        // Don't update isParent field since it's now transient
        if (orderLineDetails.getSalesrep() != null) {
            orderLine.setSalesrep(orderLineDetails.getSalesrep());
        }
        if (orderLineDetails.getBillingChannel() != null) {
            orderLine.setBillingChannel(orderLineDetails.getBillingChannel());
        }
        
        return linesRepository.save(orderLine);
    }
}

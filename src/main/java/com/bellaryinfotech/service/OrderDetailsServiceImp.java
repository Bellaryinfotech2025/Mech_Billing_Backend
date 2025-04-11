package com.bellaryinfotech.service;

 
 
 
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bellaryinfotech.model.OrderDetailsHeader;
import com.bellaryinfotech.model.OrderDetailsLookup;
import com.bellaryinfotech.repo.OrderDetailsCorelookupRepository;
import com.bellaryinfotech.repo.OrderDetailsHeaderRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderDetailsServiceImp implements OrderDetailsService {
    
    @Autowired
    private OrderDetailsCorelookupRepository lookupValueRepository;
    
    @Autowired
    private OrderDetailsHeaderRepository orderHeaderRepository;
    
    @Override
    public List<OrderDetailsLookup> getBillingFrequencies() {
        // Get all billing frequencies from CORE_LOOKUP_VALUES where LOOKUP_TYPE = 'BILLING_FREQUENCY' and ENABLED_FLAG = 'Y'
        return lookupValueRepository.findByLookupTypeAndEnabledFlag("BILLING_FREQUENCY", "Y");
    }
    
    @Override
    @Transactional
    public Map<String, Object> saveBillingFrequency(String billingFrequency) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // Create a new order with required fields
            OrderDetailsHeader order = new OrderDetailsHeader();
            
            // Don't set the order ID - let PostgreSQL generate it
            // order.setOrderId(orderId); - REMOVE THIS LINE
            
            // Set a temporary order number (will update after getting the ID)
            order.setOrderNumber("ORD-TEMP");
            
            // Set orderVersion (required field)
            order.setOrderVersion(1L);
            
            // Set status (required field)
            order.setStatus("NEW");
            
            // Set the billing frequency (this is the lookup code from the first table)
            order.setBillingFrequency(billingFrequency);
            
            // Set creation date and other required fields
            Date currentDate = new Date();
            order.setCreationDate(currentDate);
            order.setLastUpdateDate(currentDate);
            
            // Set created by and last updated by
            Long userId = 1L; // Default user ID
            order.setCreatedBy(userId);
            order.setLastUpdatedBy(userId);
            
            // Save the order to M_ORDER_HEADER_ALL
            OrderDetailsHeader savedOrder = orderHeaderRepository.save(order);
            
            // Now update the order number with the generated ID
            savedOrder.setOrderNumber("ORD-" + savedOrder.getOrderId());
            savedOrder = orderHeaderRepository.save(savedOrder);
            
            // Return success response
            result.put("status", "success");
            result.put("orderId", savedOrder.getOrderId());
            result.put("orderNumber", savedOrder.getOrderNumber());
            result.put("billingFrequency", savedOrder.getBillingFrequency());
            result.put("message", "Successfully saved billing frequency to order");
            
        } catch (Exception e) {
            e.printStackTrace();
            
            // Return error response with detailed message
            result.put("status", "error");
            result.put("message", "Failed to save billing frequency: " + e.getMessage());
        }
        
        return result;
    }
}
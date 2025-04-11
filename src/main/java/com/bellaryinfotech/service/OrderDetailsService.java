package com.bellaryinfotech.service;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bellaryinfotech.model.OrderDetailsLookup;

import java.util.List;
import java.util.Map;

public interface OrderDetailsService {
    
    List<OrderDetailsLookup> getBillingFrequencies();
    
    Map<String, Object> saveBillingFrequency(String billingFrequency);
}
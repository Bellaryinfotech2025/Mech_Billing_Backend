package com.bellaryinfotech.controller;

 
import com.bellaryinfotech.DTOImpl.CustomerAccountDTO;
import com.bellaryinfotech.DTOImpl.CustomerContactDTO;
import com.bellaryinfotech.DTOImpl.CustomerSiteDTO;
import com.bellaryinfotech.DTOImpl.OrderRequestDTO;
import com.bellaryinfotech.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    @GetMapping("/accounts")
    public ResponseEntity<List<CustomerAccountDTO>> getAllCustomerAccounts() {
        List<CustomerAccountDTO> accounts = customerService.getAllCustomerAccounts();
        return ResponseEntity.ok(accounts);
    }
    
    @GetMapping("/accounts/{custAccountId}/sites")
    public ResponseEntity<List<CustomerSiteDTO>> getCustomerSitesByAccountId(
            @PathVariable Long custAccountId) {
        List<CustomerSiteDTO> sites = customerService.getCustomerSitesByAccountId(custAccountId);
        return ResponseEntity.ok(sites);
    }
    
    @GetMapping("/accounts/{custAccountId}/contacts")
    public ResponseEntity<List<CustomerContactDTO>> getContactsByAccountId(
            @PathVariable Long custAccountId) {
        List<CustomerContactDTO> contacts = customerService.getContactsByAccountId(custAccountId);
        return ResponseEntity.ok(contacts);
    }
    
    @GetMapping("/sites/{custAcctSiteId}/contacts")
    public ResponseEntity<List<CustomerContactDTO>> getContactsBySiteId(
            @PathVariable Long custAcctSiteId) {
        List<CustomerContactDTO> contacts = customerService.getContactsBySiteId(custAcctSiteId);
        return ResponseEntity.ok(contacts);
    }
    
    
    @PostMapping("/customeridstore")
    public ResponseEntity<String> storeCustomerOrder(@RequestBody OrderRequestDTO request) {
        boolean success = customerService.storeCustomerDetailsByAccountName(request.getAccountName());
        if (success) {
            return ResponseEntity.ok("Hey! Your Order stored successfully.");
        } else {
            return ResponseEntity.badRequest().body("Hey! Failed to store order.");
        }
    }
}

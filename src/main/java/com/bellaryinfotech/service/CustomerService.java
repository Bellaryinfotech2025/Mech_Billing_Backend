package com.bellaryinfotech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bellaryinfotech.daoimpl.CustomerAccountDTO;
import com.bellaryinfotech.daoimpl.CustomerContactDTO;
import com.bellaryinfotech.daoimpl.CustomerSiteDTO;
import com.bellaryinfotech.model.CustomerAccount;
import com.bellaryinfotech.model.CustomerAccountSite;
import com.bellaryinfotech.model.CustomerContact;
import com.bellaryinfotech.model.OrderHeader;
import com.bellaryinfotech.repo.CustomerAccountRepository;
import com.bellaryinfotech.repo.CustomerContactRepository;
import com.bellaryinfotech.repo.CustomerSiteRepository;
import com.bellaryinfotech.repo.OrderHeaderRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    private CustomerSiteRepository customerSiteRepository;

    @Autowired
    private CustomerContactRepository customerContactRepository;

    @Autowired
    private OrderHeaderRepository orderHeaderRepository;

    /**
     * Stores customer order details by account name
     * 
     * @param accountName The name of the customer account
     * @return true if order was successfully stored, false otherwise
     */
    @Transactional
    public boolean storeCustomerDetailsByAccountName(String accountName) {
        try {
            // Find the customer account
            CustomerAccount account = customerAccountRepository.findByAccountName(accountName);
            if (account == null) {
                return false;
            }

            Long custAccountId = account.getCustAccountId();

            // Find the first site and contact for this account
            CustomerAccountSite site = customerSiteRepository.findFirstByCustAccountId(custAccountId);
            CustomerContact contact = customerContactRepository.findFirstByCustAccountId(custAccountId);

            if (site == null || contact == null) {
                return false;
            }

            // Create a new order header
            OrderHeader order = new OrderHeader();
            
            // Set customer, site, and contact IDs
            order.setBillToCustomerId(custAccountId);
            order.setBillToSiteId(site.getCustAcctSiteId());
            order.setBillToContactId(contact.getContactId());
            
            // Set required fields to avoid constraint violations
            
            // 1. Generate a unique order number (required field)
            String orderNumber = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            order.setOrderNumber(orderNumber);
            
            // 2. Set order version (required field)
            order.setOrderVersion(1);
            
            // 3. Set status (required field)
            order.setStatus("Draft");
            
            // 4. Set dates
            LocalDateTime now = LocalDateTime.now();
            order.setCreationDate(now);
            order.setLastUpdateDate(now);
            
            // 5. Set created by and last updated by
            Long userId = 1L; // Default user ID - replace with actual user ID if available
            order.setCreatedBy(userId);
            order.setLastUpdatedBy(userId);
            
            // 6. Set default values for other required fields
            order.setLdApplicabel("N");
            order.setBillingReviewInd("N");
            
            // Save the order
            orderHeaderRepository.save(order);
            return true;
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error storing customer order: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<CustomerAccountDTO> getAllCustomerAccounts() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<CustomerSiteDTO> getCustomerSitesByAccountId(Long custAccountId) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<CustomerContactDTO> getContactsByAccountId(Long custAccountId) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<CustomerContactDTO> getContactsBySiteId(Long custAcctSiteId) {
        // TODO Auto-generated method stub
        return null;
    }
}
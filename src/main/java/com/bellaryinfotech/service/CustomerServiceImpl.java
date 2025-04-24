package com.bellaryinfotech.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
 
import com.bellaryinfotech.DAO.CustomerDAO;
import com.bellaryinfotech.DTOImpl.OrderRequestDTO;
import com.bellaryinfotech.model.CustomerAccount;
import com.bellaryinfotech.model.CustomerAccountSite;
import com.bellaryinfotech.model.CustomerContact;
import com.bellaryinfotech.model.OrderHeader;
 
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
    
    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);
    
    @Autowired
    private CustomerDAO customerDao;

    @Override
    public ResponseEntity<?> getcustomerDtl(Integer page, Integer size, String search, Long custAccountId,
            String accountNumber, String accountName){
        LOG.info("get Customer details :: ");
        
        List<CustomerAccount> list = customerDao.getcustomerDtl(null, null, search, custAccountId, accountNumber, accountName);
        return ResponseEntity.status(HttpStatus.OK).body(list); 
    }

    @Override
    public ResponseEntity<?> getAllSitesDetails(Integer page, Integer size, String search, Long custAccountId, 
            Long custAcctSiteId, String siteName) {
        LOG.info("get All Sites details :: ");
        
        List<CustomerAccountSite> list = customerDao.getAllSitesDetails(page, size, search, custAccountId, custAcctSiteId, siteName);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    
    @Override
    public ResponseEntity<?> getAllContactsDetails(Integer page, Integer size, String search, Long custAccountId, 
            Long custAcctSiteId, Long contactId, String roleType) {
        LOG.info("get All Contacts details :: ");
        
        List<CustomerContact> list = customerDao.getAllContactsDetails(page, size, search, custAccountId, custAcctSiteId, contactId, roleType);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    
    @Override
    public ResponseEntity<?> fetchCustomerIdAndSave(OrderRequestDTO orderRequestDTO) {
        LOG.info("Fetching customer IDs and saving to OrderHeader :: ");
        
        if (orderRequestDTO.getAccountName() == null || orderRequestDTO.getAccountName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account name is required");
        }
        
        OrderHeader orderHeader = customerDao.fetchCustomerIdAndSave(orderRequestDTO);
        
        if (orderHeader == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer account not found with name: " + orderRequestDTO.getAccountName());
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(orderHeader);
    }
}
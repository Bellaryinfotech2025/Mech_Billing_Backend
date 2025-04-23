package com.bellaryinfotech.service;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bellaryinfotech.daoclasses.CustomerDAO;
import com.bellaryinfotech.daoimpl.CustomerAccountDTO;
import com.bellaryinfotech.daoimpl.CustomerContactDTO;
import com.bellaryinfotech.daoimpl.CustomerSiteDTO;

import java.util.List;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerDAO customerDAO;
    
    public List<CustomerAccountDTO> getAllCustomerAccounts() {
        return customerDAO.getAllCustomerAccounts();
    }
    
    public List<CustomerSiteDTO> getCustomerSitesByAccountId(Long custAccountId) {
        return customerDAO.getCustomerSitesByAccountId(custAccountId);
    }
    
    public List<CustomerContactDTO> getContactsByAccountId(Long custAccountId) {
        return customerDAO.getContactsByAccountId(custAccountId);
    }
    
    public List<CustomerContactDTO> getContactsBySiteId(Long custAcctSiteId) {
        return customerDAO.getContactsBySiteId(custAcctSiteId);
    }
}
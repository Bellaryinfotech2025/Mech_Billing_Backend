package com.bellaryinfotech.DAO;

 

 
import com.bellaryinfotech.DTOImpl.CustomerAccountDTO;
import com.bellaryinfotech.DTOImpl.CustomerContactDTO;
import com.bellaryinfotech.DTOImpl.CustomerSiteDTO;
import com.bellaryinfotech.model.CustomerAccount;
import com.bellaryinfotech.model.CustomerAccountSite;
import com.bellaryinfotech.model.CustomerContact;
import com.bellaryinfotech.repo.CustomerAccountRepository;
import com.bellaryinfotech.repo.CustomerContactRepository;
import com.bellaryinfotech.repo.CustomerSiteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerDAO {
    
    @Autowired
    private CustomerAccountRepository customerAccountRepository;
    
    @Autowired
    private CustomerSiteRepository customerSiteRepository;
    
    @Autowired
    private CustomerContactRepository customerContactRepository;
    
    public List<CustomerAccountDTO> getAllCustomerAccounts() {
        List<CustomerAccount> accounts = customerAccountRepository.findByAccountNameIsNotNull();
        return accounts.stream()
                .map(account -> new CustomerAccountDTO(
                        account.getCustAccountId(),
                        account.getAccountNumber(),
                        account.getAccountName()))
                .collect(Collectors.toList());
    }
    
    public List<CustomerSiteDTO> getCustomerSitesByAccountId(Long custAccountId) {
        List<CustomerAccountSite> sites = customerSiteRepository.findByCustAccountId(custAccountId);
        return sites.stream()
                .map(site -> new CustomerSiteDTO(
                        site.getCustAcctSiteId(),
                        site.getCustAccountId(),
                        site.getSiteName(),
                        site.getAddress1(),
                        site.getCity(),
                        site.getState(),
                        site.getCountry()))
                .collect(Collectors.toList());
    }
    
    public List<CustomerContactDTO> getContactsByAccountId(Long custAccountId) {
        List<CustomerContact> contacts = customerContactRepository.findByCustAccountId(custAccountId);
        return contacts.stream()
                .map(contact -> new CustomerContactDTO(
                        contact.getContactId(),
                        contact.getCustAccountId(),
                        contact.getCustAcctSiteId(),
                        contact.getRoleType()))
                .collect(Collectors.toList());
    }
    
    public List<CustomerContactDTO> getContactsBySiteId(Long custAcctSiteId) {
        List<CustomerContact> contacts = customerContactRepository.findByCustAcctSiteId(custAcctSiteId);
        return contacts.stream()
                .map(contact -> new CustomerContactDTO(
                        contact.getContactId(),
                        contact.getCustAccountId(),
                        contact.getCustAcctSiteId(),
                        contact.getRoleType()))
                .collect(Collectors.toList());
    }
}
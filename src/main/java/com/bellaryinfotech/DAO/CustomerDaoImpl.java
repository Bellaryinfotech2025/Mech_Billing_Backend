package com.bellaryinfotech.DAO;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bellaryinfotech.DTOImpl.OrderRequestDTO;
import com.bellaryinfotech.model.CustomerAccount;
import com.bellaryinfotech.model.CustomerAccountSite;
import com.bellaryinfotech.model.CustomerContact;
import com.bellaryinfotech.model.OrderHeader;
import com.bellaryinfotech.repo.CustomerAccountRepository;
import com.bellaryinfotech.repo.CustomerContactRepository;
import com.bellaryinfotech.repo.CustomerSiteRepository;
import com.bellaryinfotech.repo.OrderHeaderRepository;
import com.bellaryinfotech.service.CustomerServiceImpl;

@Repository
public class CustomerDaoImpl extends GenericDAOImpl implements CustomerDAO {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private CustomerAccountRepository customerAccountRepository;
    
    @Autowired
    private CustomerSiteRepository customerSiteRepository;
    
    @Autowired
    private CustomerContactRepository customerContactRepository;
    
    @Autowired
    private OrderHeaderRepository orderHeaderRepository;

    @Override
    public List<CustomerAccount> getcustomerDtl(Integer page, Integer size, String search, Long custAccountId,
                                                String accountNumber, String accountName) {
        LOG.info("Get CustomerDAOIMPL");

        StringBuilder hql = new StringBuilder("SELECT irc FROM CustomerAccount irc WHERE 1=1");

        if (custAccountId != null) {
            hql.append(" AND irc.custAccountId = :custAccountId");
        }
        if (search != null && !search.isEmpty()) {
            hql.append(" AND UPPER(irc.ruleName) LIKE :search");
        }

        TypedQuery<CustomerAccount> query = entityManager.createQuery(hql.toString(), CustomerAccount.class);

        if (custAccountId != null) {
            query.setParameter("custAccountId", custAccountId);
        }
        if (search != null && !search.isEmpty()) {
            query.setParameter("search", "%" + search.toUpperCase().replace("'", "''") + "%");
        }

        if (page != null && size != null) {
            query.setFirstResult(page).setMaxResults(size);
        }

        List<CustomerAccount> invlist = query.getResultList();

        LOG.info("Fetched {} customer accounts", invlist.size());

        return invlist;
    }
    
    @Override
    public List<CustomerAccountSite> getAllSitesDetails(Integer page, Integer size, String search, Long custAccountId, 
            Long custAcctSiteId, String siteName) {
        LOG.info("Get All Sites Details");

        StringBuilder hql = new StringBuilder("SELECT site FROM CustomerAccountSite site WHERE 1=1");

        if (custAccountId != null) {
            hql.append(" AND site.custAccountId = :custAccountId");
        }
        if (custAcctSiteId != null) {
            hql.append(" AND site.custAcctSiteId = :custAcctSiteId");
        }
        if (siteName != null && !siteName.isEmpty()) {
            hql.append(" AND UPPER(site.siteName) LIKE :siteName");
        }
        if (search != null && !search.isEmpty()) {
            hql.append(" AND (UPPER(site.siteName) LIKE :search OR UPPER(site.city) LIKE :search OR UPPER(site.state) LIKE :search OR UPPER(site.country) LIKE :search)");
        }

        TypedQuery<CustomerAccountSite> query = entityManager.createQuery(hql.toString(), CustomerAccountSite.class);

        if (custAccountId != null) {
            query.setParameter("custAccountId", custAccountId);
        }
        if (custAcctSiteId != null) {
            query.setParameter("custAcctSiteId", custAcctSiteId);
        }
        if (siteName != null && !siteName.isEmpty()) {
            query.setParameter("siteName", "%" + siteName.toUpperCase().replace("'", "''") + "%");
        }
        if (search != null && !search.isEmpty()) {
            query.setParameter("search", "%" + search.toUpperCase().replace("'", "''") + "%");
        }

        if (page != null && size != null) {
            query.setFirstResult(page).setMaxResults(size);
        }

        List<CustomerAccountSite> siteList = query.getResultList();

        LOG.info("Fetched {} customer sites", siteList.size());

        return siteList;
    }
    
    @Override
    public List<CustomerContact> getAllContactsDetails(Integer page, Integer size, String search, Long custAccountId, 
            Long custAcctSiteId, Long contactId, String roleType) {
        LOG.info("Get All Contacts Details");

        StringBuilder hql = new StringBuilder("SELECT contact FROM CustomerContact contact WHERE 1=1");

        if (custAccountId != null) {
            hql.append(" AND contact.custAccountId = :custAccountId");
        }
        if (custAcctSiteId != null) {
            hql.append(" AND contact.custAcctSiteId = :custAcctSiteId");
        }
        if (contactId != null) {
            hql.append(" AND contact.contactId = :contactId");
        }
        if (roleType != null && !roleType.isEmpty()) {
            hql.append(" AND UPPER(contact.roleType) LIKE :roleType");
        }
        if (search != null && !search.isEmpty()) {
            hql.append(" AND UPPER(contact.roleType) LIKE :search");
        }

        TypedQuery<CustomerContact> query = entityManager.createQuery(hql.toString(), CustomerContact.class);

        if (custAccountId != null) {
            query.setParameter("custAccountId", custAccountId);
        }
        if (custAcctSiteId != null) {
            query.setParameter("custAcctSiteId", custAcctSiteId);
        }
        if (contactId != null) {
            query.setParameter("contactId", contactId);
        }
        if (roleType != null && !roleType.isEmpty()) {
            query.setParameter("roleType", "%" + roleType.toUpperCase().replace("'", "''") + "%");
        }
        if (search != null && !search.isEmpty()) {
            query.setParameter("search", "%" + search.toUpperCase().replace("'", "''") + "%");
        }

        if (page != null && size != null) {
            query.setFirstResult(page).setMaxResults(size);
        }

        List<CustomerContact> contactList = query.getResultList();

        LOG.info("Fetched {} customer contacts", contactList.size());

        return contactList;
    }
    
    @Override
    public OrderHeader fetchCustomerIdAndSave(OrderRequestDTO orderRequestDTO) {
        LOG.info("Fetching customer IDs and saving to OrderHeader");
        
        // Find customer account by account name
        CustomerAccount customerAccount = customerAccountRepository.findByAccountName(orderRequestDTO.getAccountName());
        
        if (customerAccount == null) {
            LOG.error("Customer account not found with name: {}", orderRequestDTO.getAccountName());
            return null;
        }
        
        Long custAccountId = customerAccount.getCustAccountId();
        LOG.info("Found customer account with ID: {}", custAccountId);
        
        // Find first site for this customer
        CustomerAccountSite customerSite = customerSiteRepository.findFirstByCustAccountId(custAccountId);
        Long custAcctSiteId = null;
        if (customerSite != null) {
            custAcctSiteId = customerSite.getCustAcctSiteId();
            LOG.info("Found customer site with ID: {}", custAcctSiteId);
        } else {
            LOG.warn("No site found for customer ID: {}", custAccountId);
        }
        
        // Find first contact for this customer
        CustomerContact customerContact = customerContactRepository.findFirstByCustAccountId(custAccountId);
        Long contactId = null;
        if (customerContact != null) {
            contactId = customerContact.getContactId();
            LOG.info("Found customer contact with ID: {}", contactId);
        } else {
            LOG.warn("No contact found for customer ID: {}", custAccountId);
        }
        
        // Create and save order header
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setBillToCustomerId(custAccountId);
        orderHeader.setBillToSiteId(custAcctSiteId);
        orderHeader.setBillToContactId(contactId);
        
        // Set required fields
        orderHeader.setOrderNumber("ORD-" + System.currentTimeMillis());
        orderHeader.setOrderVersion(1);
        orderHeader.setStatus("DRAFT");
        orderHeader.setCreationDate(LocalDateTime.now());
        orderHeader.setLastUpdateDate(LocalDateTime.now());
        orderHeader.setCreatedBy(1L); // Default user ID
        orderHeader.setLastUpdatedBy(1L); // Default user ID
        
        // Save and return the order header
        OrderHeader savedOrderHeader = orderHeaderRepository.save(orderHeader);
        LOG.info("Saved OrderHeader with order number: {}", savedOrderHeader.getOrderNumber());
        
        return savedOrderHeader;
    }
}
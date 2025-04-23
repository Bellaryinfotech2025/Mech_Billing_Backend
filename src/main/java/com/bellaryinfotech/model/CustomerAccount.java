package com.bellaryinfotech.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "CUST_ACCOUNTS")
public class CustomerAccount {
    
    @Id
    @Column(name = "CUST_ACCOUNT_ID", nullable = false)
    private Long custAccountId;
    
    @Column(name = "ACCOUNT_NUMBER", nullable = false, length = 30)
    private String accountNumber;
    
    @Column(name = "ACCOUNT_NAME", length = 240)
    private String accountName;
    
    @Column(name = "LAST_UPDATE_DATE", nullable = false)
    private Date lastUpdateDate;
    
    @Column(name = "LAST_UPDATED_BY", nullable = false)
    private Long lastUpdatedBy;
    
    @Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;
    
    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;
    
    // Constructors
    public CustomerAccount() {
    }
    
    // Getters and setters
    public Long getCustAccountId() {
        return custAccountId;
    }
    
    public void setCustAccountId(Long custAccountId) {
        this.custAccountId = custAccountId;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public String getAccountName() {
        return accountName;
    }
    
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    
    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    
    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public Long getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
}
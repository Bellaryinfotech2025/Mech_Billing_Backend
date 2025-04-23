package com.bellaryinfotech.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "CUST_ACCT_CONTACTS")
public class CustomerContact {
    
    @Id
    @Column(name = "CONTACT_ID", nullable = false)
    private Long contactId;
    
    @Column(name = "CUST_ACCOUNT_ID")
    private Long custAccountId;
    
    @Column(name = "CUST_ACCT_SITE_ID")
    private Long custAcctSiteId;
    
    @Column(name = "PRIMARY_FLAG", length = 1)
    private String primaryFlag;
    
    @Column(name = "ROLE_TYPE", length = 30)
    private String roleType;
    
    @Column(name = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;
    
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    
    @Column(name = "CREATED_BY")
    private Long createdBy;
    
    // Constructors
    public CustomerContact() {
    }
    
    // Getters and setters
    public Long getContactId() {
        return contactId;
    }
    
    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }
    
    public Long getCustAccountId() {
        return custAccountId;
    }
    
    public void setCustAccountId(Long custAccountId) {
        this.custAccountId = custAccountId;
    }
    
    public Long getCustAcctSiteId() {
        return custAcctSiteId;
    }
    
    public void setCustAcctSiteId(Long custAcctSiteId) {
        this.custAcctSiteId = custAcctSiteId;
    }
    
    public String getPrimaryFlag() {
        return primaryFlag;
    }
    
    public void setPrimaryFlag(String primaryFlag) {
        this.primaryFlag = primaryFlag;
    }
    
    public String getRoleType() {
        return roleType;
    }
    
    public void setRoleType(String roleType) {
        this.roleType = roleType;
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
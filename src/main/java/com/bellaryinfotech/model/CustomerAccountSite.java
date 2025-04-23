package com.bellaryinfotech.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "CUST_ACCT_SITES_ALL")
public class CustomerAccountSite {
    
    @Id
    @Column(name = "CUST_ACCT_SITE_ID", nullable = false)
    private Long custAcctSiteId;
    
    @Column(name = "CUST_ACCOUNT_ID", nullable = false)
    private Long custAccountId;
    
    @Column(name = "SITE_NAME", length = 240)
    private String siteName;
    
    @Column(name = "ADDRESS1", length = 250)
    private String address1;
    
    @Column(name = "CITY", length = 250)
    private String city;
    
    @Column(name = "STATE", length = 250)
    private String state;
    
    @Column(name = "COUNTRY", length = 250)
    private String country;
    
    @Column(name = "LAST_UPDATE_DATE", nullable = false)
    private Date lastUpdateDate;
    
    @Column(name = "LAST_UPDATED_BY", nullable = false)
    private Long lastUpdatedBy;
    
    @Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;
    
    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;
    
    // Constructors
    public CustomerAccountSite() {
    }
    
    // Getters and setters
    public Long getCustAcctSiteId() {
        return custAcctSiteId;
    }
    
    public void setCustAcctSiteId(Long custAcctSiteId) {
        this.custAcctSiteId = custAcctSiteId;
    }
    
    public Long getCustAccountId() {
        return custAccountId;
    }
    
    public void setCustAccountId(Long custAccountId) {
        this.custAccountId = custAccountId;
    }
    
    public String getSiteName() {
        return siteName;
    }
    
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    
    public String getAddress1() {
        return address1;
    }
    
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
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
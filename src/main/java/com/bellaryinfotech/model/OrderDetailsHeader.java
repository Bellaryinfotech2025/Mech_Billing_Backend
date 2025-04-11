package com.bellaryinfotech.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "M_ORDER_HEADER_ALL")
public class OrderDetailsHeader {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID", nullable = false)
    private Long orderId;
    
    @Column(name = "ORDER_NUMBER", nullable = false)
    private String orderNumber;
    
    @Column(name = "ORDER_VERSION", nullable = false)
    private Long orderVersion;
    
    @Column(name = "STATUS", nullable = false)
    private String status;
    
    @Column(name = "BILLING_FREQUENCY")
    private String billingFrequency;
    
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    
    @Column(name = "CREATED_BY")
    private Long createdBy;
    
    @Column(name = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;
    
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    
    // Default constructor
    public OrderDetailsHeader() {
    }
    
    public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Long getOrderVersion() {
		return orderVersion;
	}

	public void setOrderVersion(Long orderVersion) {
		this.orderVersion = orderVersion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBillingFrequency() {
		return billingFrequency;
	}

	public void setBillingFrequency(String billingFrequency) {
		this.billingFrequency = billingFrequency;
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

	// Getters and setters
    public Long getOrderId() {
        return orderId;
    }
    
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
    // Other getters and setters remain the same
    // ...
}
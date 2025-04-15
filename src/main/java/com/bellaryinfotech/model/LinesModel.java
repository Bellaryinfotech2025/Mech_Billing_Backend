package com.bellaryinfotech.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "M_ORDER_LINES_ALL")
public class LinesModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LINE_ID")
    private Long lineId;
    
    @Column(name = "ORDER_ID", nullable = false)
    private Long orderId;
    
    @Column(name = "PARENT_LINE_NUMBER")
    private Integer parentLineNumber;
    
    @Column(name = "LINE_NUMBER", nullable = false)
    private Integer lineNumber;
    
    @Column(name = "LINE_TYPE", length = 50)
    private String lineType;
    
    @Column(name = "ITEM_SOURCE", length = 50)
    private String itemSource;
    
    @Column(name = "SERVICE_NAME", length = 500)
    private String serviceName;
    
    @Column(name = "ITEM_ID")
    private Long itemId;
    
    @Column(name = "ITEM_DESCRIPTION", length = 240)
    private String itemDescription;
    
    @Column(name = "STATUS", length = 50)
    private String status;
    
    @Column(name = "EFFECTIVE_START_DATE")
    private LocalDate effectiveStartDate;
    
    @Column(name = "EFFECTIVE_END_DATE")
    private LocalDate effectiveEndDate;
    
    @Column(name = "UOM", length = 20)
    private String uom;
    
    @Column(name = "ORDERED_QUANTITY")
    private BigDecimal orderedQuantity;
    
    @Column(name = "DELIVERED_QUANTITY")
    private BigDecimal deliveredQuantity;
    
    @Column(name = "BILLING_QUANTITY")
    private BigDecimal billingQuantity;
    
    @Column(name = "BILLED_QUANTITY")
    private BigDecimal billedQuantity;
    
    @Column(name = "MAX_QUANTITY")
    private BigDecimal maxQuantity;
    
    @Column(name = "CLICKED_QUANTITY")
    private BigDecimal clickedQuantity;
    
    @Column(name = "TERMINATED_QUANTITY")
    private BigDecimal terminatedQuantity;
    
    @Column(name = "BOOKED_DATE")
    private LocalDate bookedDate;
    
    @Column(name = "ACTIVATION_DATE")
    private LocalDate activationDate;
    
    @Column(name = "DESCRIPTION", length = 2000)
    private String description;
    
    @Column(name = "VERSION")
    private Integer version;
    
    @Column(name = "SOURCE")
    private Integer source;
    
    @Column(name = "BILLING_STATUS", length = 30)
    private String billingStatus;
    
    @Column(name = "BILL_TO_CUSTOMER_ID")
    private Long billToCustomerId;
    
    @Column(name = "BILL_TO_SITE_ID")
    private Long billToSiteId;
    
    @Column(name = "BILL_TO_CONTACT_ID")
    private Long billToContactId;
    
    @Column(name = "SELL_TO_CUSTOMER_ID")
    private Long sellToCustomerId;
    
    @Column(name = "SELL_TO_SITE_ID")
    private Long sellToSiteId;
    
    @Column(name = "SELL_TO_CONTACT_ID")
    private Long sellToContactId;
    
    @Column(name = "BILLING_CYCLE", length = 25)
    private String billingCycle;
    
    @Column(name = "BILLING_FREQUENCY", length = 50)
    private String billingFrequency;
    
    @Column(name = "INVOICING_RULE", length = 50)
    private String invoicingRule;
    
    @Column(name = "PO_NUMBER", length = 150)
    private String poNumber;
    
    @Column(name = "CREATION_DATE")
    private LocalDate creationDate;
    
    @Column(name = "CREATED_BY")
    private Long createdBy;
    
    @Column(name = "LAST_UPDATE_DATE")
    private LocalDate lastUpdateDate;
    
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    
    @Column(name = "SOURCE_ID", length = 50)
    private String sourceId;
    
    @Column(name = "ORG_ID")
    private Long orgId;
    
    @Column(name = "TENANT_ID")
    private Long tenantId;
    
    @Column(name = "ATTRIBUTE_CONTEXT", length = 150)
    private String attributeContext;
    
    @Column(name = "ATTRIBUTE1_D")
    private LocalDate attribute1D;
    
    @Column(name = "ATTRIBUTE2_D")
    private LocalDate attribute2D;
    
    @Column(name = "ATTRIBUTE3_D")
    private LocalDate attribute3D;
    
    @Column(name = "ATTRIBUTE4_D")
    private LocalDate attribute4D;
    
    @Column(name = "ATTRIBUTE5_D")
    private LocalDate attribute5D;
    
    @Column(name = "ATTRIBUTE1_N")
    private BigDecimal attribute1N;
    
    @Column(name = "ATTRIBUTE2_N")
    private BigDecimal attribute2N;
    
    @Column(name = "ATTRIBUTE3_N")
    private BigDecimal attribute3N;
    
    @Column(name = "ATTRIBUTE4_N")
    private BigDecimal attribute4N;
    
    @Column(name = "ATTRIBUTE5_N")
    private BigDecimal attribute5N;
    
    @Column(name = "ATTRIBUTE1_V", length = 250)
    private String attribute1V;
    
    @Column(name = "ATTRIBUTE2_V", length = 250)
    private String attribute2V;
    
    @Column(name = "ATTRIBUTE3_V", length = 250)
    private String attribute3V;
    
    @Column(name = "ATTRIBUTE4_V", length = 250)
    private String attribute4V;
    
    @Column(name = "ATTRIBUTE5_V", length = 250)
    private String attribute5V;
    
    @Column(name = "ATTRIBUTE6_V", length = 250)
    private String attribute6V;
    
    @Column(name = "ATTRIBUTE7_V", length = 250)
    private String attribute7V;
    
    @Column(name = "ATTRIBUTE8_V", length = 250)
    private String attribute8V;
    
    @Column(name = "ATTRIBUTE9_V", length = 250)
    private String attribute9V;
    
    @Column(name = "ATTRIBUTE10_V", length = 250)
    private String attribute10V;
    
    @Column(name = "ATTRIBUTE11_V", length = 250)
    private String attribute11V;
    
    @Column(name = "ATTRIBUTE12_V", length = 250)
    private String attribute12V;
    
    @Column(name = "ATTRIBUTE13_V", length = 250)
    private String attribute13V;
    
    @Column(name = "ATTRIBUTE14_V", length = 250)
    private String attribute14V;
    
    @Column(name = "ATTRIBUTE15_V", length = 250)
    private String attribute15V;
    
    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;
    
    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;
    
    @Transient
    private Boolean isParent;
    
    @Column(name = "SALESREP", length = 100)
    private String salesrep;
    
    @Transient
    private String billingChannel;
    
    // Default constructor
    public LinesModel() {
    }
    
    // Getters and Setters
    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getParentLineNumber() {
        return parentLineNumber;
    }

    public void setParentLineNumber(Integer parentLineNumber) {
        this.parentLineNumber = parentLineNumber;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getItemSource() {
        return itemSource;
    }

    public void setItemSource(String itemSource) {
        this.itemSource = itemSource;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(LocalDate effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public LocalDate getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(LocalDate effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public BigDecimal getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(BigDecimal orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public BigDecimal getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(BigDecimal deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    public BigDecimal getBillingQuantity() {
        return billingQuantity;
    }

    public void setBillingQuantity(BigDecimal billingQuantity) {
        this.billingQuantity = billingQuantity;
    }

    public BigDecimal getBilledQuantity() {
        return billedQuantity;
    }

    public void setBilledQuantity(BigDecimal billedQuantity) {
        this.billedQuantity = billedQuantity;
    }

    public BigDecimal getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(BigDecimal maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public BigDecimal getClickedQuantity() {
        return clickedQuantity;
    }

    public void setClickedQuantity(BigDecimal clickedQuantity) {
        this.clickedQuantity = clickedQuantity;
    }

    public BigDecimal getTerminatedQuantity() {
        return terminatedQuantity;
    }

    public void setTerminatedQuantity(BigDecimal terminatedQuantity) {
        this.terminatedQuantity = terminatedQuantity;
    }

    public LocalDate getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(LocalDate bookedDate) {
        this.bookedDate = bookedDate;
    }

    public LocalDate getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDate activationDate) {
        this.activationDate = activationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(String billingStatus) {
        this.billingStatus = billingStatus;
    }

    public Long getBillToCustomerId() {
        return billToCustomerId;
    }

    public void setBillToCustomerId(Long billToCustomerId) {
        this.billToCustomerId = billToCustomerId;
    }

    public Long getBillToSiteId() {
        return billToSiteId;
    }

    public void setBillToSiteId(Long billToSiteId) {
        this.billToSiteId = billToSiteId;
    }

    public Long getBillToContactId() {
        return billToContactId;
    }

    public void setBillToContactId(Long billToContactId) {
        this.billToContactId = billToContactId;
    }

    public Long getSellToCustomerId() {
        return sellToCustomerId;
    }

    public void setSellToCustomerId(Long sellToCustomerId) {
        this.sellToCustomerId = sellToCustomerId;
    }

    public Long getSellToSiteId() {
        return sellToSiteId;
    }

    public void setSellToSiteId(Long sellToSiteId) {
        this.sellToSiteId = sellToSiteId;
    }

    public Long getSellToContactId() {
        return sellToContactId;
    }

    public void setSellToContactId(Long sellToContactId) {
        this.sellToContactId = sellToContactId;
    }

    public String getBillingCycle() {
        return billingCycle;
    }

    public void setBillingCycle(String billingCycle) {
        this.billingCycle = billingCycle;
    }

    public String getBillingFrequency() {
        return billingFrequency;
    }

    public void setBillingFrequency(String billingFrequency) {
        this.billingFrequency = billingFrequency;
    }

    public String getInvoicingRule() {
        return invoicingRule;
    }

    public void setInvoicingRule(String invoicingRule) {
        this.invoicingRule = invoicingRule;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getAttributeContext() {
        return attributeContext;
    }

    public void setAttributeContext(String attributeContext) {
        this.attributeContext = attributeContext;
    }

    public LocalDate getAttribute1D() {
        return attribute1D;
    }

    public void setAttribute1D(LocalDate attribute1d) {
        attribute1D = attribute1d;
    }

    public LocalDate getAttribute2D() {
        return attribute2D;
    }

    public void setAttribute2D(LocalDate attribute2d) {
        attribute2D = attribute2d;
    }

    public LocalDate getAttribute3D() {
        return attribute3D;
    }

    public void setAttribute3D(LocalDate attribute3d) {
        attribute3D = attribute3d;
    }

    public LocalDate getAttribute4D() {
        return attribute4D;
    }

    public void setAttribute4D(LocalDate attribute4d) {
        attribute4D = attribute4d;
    }

    public LocalDate getAttribute5D() {
        return attribute5D;
    }

    public void setAttribute5D(LocalDate attribute5d) {
        attribute5D = attribute5d;
    }

    public BigDecimal getAttribute1N() {
        return attribute1N;
    }

    public void setAttribute1N(BigDecimal attribute1n) {
        attribute1N = attribute1n;
    }

    public BigDecimal getAttribute2N() {
        return attribute2N;
    }

    public void setAttribute2N(BigDecimal attribute2n) {
        attribute2N = attribute2n;
    }

    public BigDecimal getAttribute3N() {
        return attribute3N;
    }

    public void setAttribute3N(BigDecimal attribute3n) {
        attribute3N = attribute3n;
    }

    public BigDecimal getAttribute4N() {
        return attribute4N;
    }

    public void setAttribute4N(BigDecimal attribute4n) {
        attribute4N = attribute4n;
    }

    public BigDecimal getAttribute5N() {
        return attribute5N;
    }

    public void setAttribute5N(BigDecimal attribute5n) {
        attribute5N = attribute5n;
    }

    public String getAttribute1V() {
        return attribute1V;
    }

    public void setAttribute1V(String attribute1v) {
        attribute1V = attribute1v;
    }

    public String getAttribute2V() {
        return attribute2V;
    }

    public void setAttribute2V(String attribute2v) {
        attribute2V = attribute2v;
    }

    public String getAttribute3V() {
        return attribute3V;
    }

    public void setAttribute3V(String attribute3v) {
        attribute3V = attribute3v;
    }

    public String getAttribute4V() {
        return attribute4V;
    }

    public void setAttribute4V(String attribute4v) {
        attribute4V = attribute4v;
    }

    public String getAttribute5V() {
        return attribute5V;
    }

    public void setAttribute5V(String attribute5v) {
        attribute5V = attribute5v;
    }

    public String getAttribute6V() {
        return attribute6V;
    }

    public void setAttribute6V(String attribute6v) {
        attribute6V = attribute6v;
    }

    public String getAttribute7V() {
        return attribute7V;
    }

    public void setAttribute7V(String attribute7v) {
        attribute7V = attribute7v;
    }

    public String getAttribute8V() {
        return attribute8V;
    }

    public void setAttribute8V(String attribute8v) {
        attribute8V = attribute8v;
    }

    public String getAttribute9V() {
        return attribute9V;
    }

    public void setAttribute9V(String attribute9v) {
        attribute9V = attribute9v;
    }

    public String getAttribute10V() {
        return attribute10V;
    }

    public void setAttribute10V(String attribute10v) {
        attribute10V = attribute10v;
    }

    public String getAttribute11V() {
        return attribute11V;
    }

    public void setAttribute11V(String attribute11v) {
        attribute11V = attribute11v;
    }

    public String getAttribute12V() {
        return attribute12V;
    }

    public void setAttribute12V(String attribute12v) {
        attribute12V = attribute12v;
    }

    public String getAttribute13V() {
        return attribute13V;
    }

    public void setAttribute13V(String attribute13v) {
        attribute13V = attribute13v;
    }

    public String getAttribute14V() {
        return attribute14V;
    }

    public void setAttribute14V(String attribute14v) {
        attribute14V = attribute14v;
    }

    public String getAttribute15V() {
        return attribute15V;
    }

    public void setAttribute15V(String attribute15v) {
        attribute15V = attribute15v;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public String getSalesrep() {
        return salesrep;
    }

    public void setSalesrep(String salesrep) {
        this.salesrep = salesrep;
    }

    public String getBillingChannel() {
        return billingChannel;
    }

    public void setBillingChannel(String billingChannel) {
        this.billingChannel = billingChannel;
    }
    
    @PrePersist
    protected void onCreate() {
        creationDate = LocalDate.now();
        lastUpdateDate = LocalDate.now();
        createdBy = 1L; // Default user ID
        lastUpdatedBy = 1L; // Default user ID
    }
    
    @PreUpdate
    protected void onUpdate() {
        lastUpdateDate = LocalDate.now();
        lastUpdatedBy = 1L; // Default user ID
    }
}

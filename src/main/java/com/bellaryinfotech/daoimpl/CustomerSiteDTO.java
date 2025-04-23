package com.bellaryinfotech.daoimpl;

 

public class CustomerSiteDTO {
    private Long custAcctSiteId;
    private Long custAccountId;
    private String siteName;
    private String address;
    private String city;
    private String state;
    private String country;
    
    public CustomerSiteDTO() {
    }
    
    public CustomerSiteDTO(Long custAcctSiteId, Long custAccountId, String siteName, String address, String city, String state, String country) {
        this.custAcctSiteId = custAcctSiteId;
        this.custAccountId = custAccountId;
        this.siteName = siteName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
    }
    
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
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
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
}
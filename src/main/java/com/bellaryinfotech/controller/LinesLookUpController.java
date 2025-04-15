package com.bellaryinfotech.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lookups")
@CrossOrigin(origins = "*")
public class LinesLookUpController {

    @GetMapping("/billing-frequencies")
    public ResponseEntity<Map<String, Object>> getBillingFrequencies() {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> billingFrequencies = new ArrayList<>();
        
        Map<String, String> monthly = new HashMap<>();
        monthly.put("lookupCode", "MONTHLY");
        monthly.put("meaning", "Monthly");
        billingFrequencies.add(monthly);
        
        Map<String, String> quarterly = new HashMap<>();
        quarterly.put("lookupCode", "QUARTERLY");
        quarterly.put("meaning", "Quarterly");
        billingFrequencies.add(quarterly);
        
        Map<String, String> halfYearly = new HashMap<>();
        halfYearly.put("lookupCode", "HALF_YEARLY");
        halfYearly.put("meaning", "Half Yearly");
        billingFrequencies.add(halfYearly);
        
        Map<String, String> yearly = new HashMap<>();
        yearly.put("lookupCode", "YEARLY");
        yearly.put("meaning", "Yearly");
        billingFrequencies.add(yearly);
        
        Map<String, String> oneTime = new HashMap<>();
        oneTime.put("lookupCode", "ONE_TIME");
        oneTime.put("meaning", "One Time");
        billingFrequencies.add(oneTime);
        
        response.put("status", "success");
        response.put("billingFrequencies", billingFrequencies);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/billing-channels")
    public ResponseEntity<Map<String, Object>> getBillingChannels() {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> billingChannels = new ArrayList<>();
        
        Map<String, String> email = new HashMap<>();
        email.put("lookupCode", "EMAIL");
        email.put("meaning", "Email");
        billingChannels.add(email);
        
        Map<String, String> mail = new HashMap<>();
        mail.put("lookupCode", "MAIL");
        mail.put("meaning", "Mail");
        billingChannels.add(mail);
        
        Map<String, String> portal = new HashMap<>();
        portal.put("lookupCode", "PORTAL");
        portal.put("meaning", "Portal");
        billingChannels.add(portal);
        
        Map<String, String> inPerson = new HashMap<>();
        inPerson.put("lookupCode", "IN_PERSON");
        inPerson.put("meaning", "In Person");
        billingChannels.add(inPerson);
        
        response.put("status", "success");
        response.put("billingChannels", billingChannels);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/uom")
    public ResponseEntity<Map<String, Object>> getUnitOfMeasures() {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> uomList = new ArrayList<>();
        
        Map<String, String> each = new HashMap<>();
        each.put("lookupCode", "EA");
        each.put("meaning", "Each (EA)");
        uomList.add(each);
        
        Map<String, String> hour = new HashMap<>();
        hour.put("lookupCode", "HR");
        hour.put("meaning", "Hour (HR)");
        uomList.add(hour);
        
        Map<String, String> day = new HashMap<>();
        day.put("lookupCode", "DAY");
        day.put("meaning", "Day (DAY)");
        uomList.add(day);
        
        Map<String, String> month = new HashMap<>();
        month.put("lookupCode", "MTH");
        month.put("meaning", "Month (MTH)");
        uomList.add(month);
        
        Map<String, String> year = new HashMap<>();
        year.put("lookupCode", "YR");
        year.put("meaning", "Year (YR)");
        uomList.add(year);
        
        response.put("status", "success");
        response.put("uomList", uomList);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all-lookups")
    public ResponseEntity<Map<String, Object>> getAllLookups() {
        Map<String, Object> response = new HashMap<>();
        
        // Billing Frequencies
        List<Map<String, String>> billingFrequencies = new ArrayList<>();
        
        Map<String, String> monthly = new HashMap<>();
        monthly.put("lookupCode", "MONTHLY");
        monthly.put("meaning", "Monthly");
        billingFrequencies.add(monthly);
        
        Map<String, String> quarterly = new HashMap<>();
        quarterly.put("lookupCode", "QUARTERLY");
        quarterly.put("meaning", "Quarterly");
        billingFrequencies.add(quarterly);
        
        Map<String, String> halfYearly = new HashMap<>();
        halfYearly.put("lookupCode", "HALF_YEARLY");
        halfYearly.put("meaning", "Half Yearly");
        billingFrequencies.add(halfYearly);
        
        Map<String, String> yearly = new HashMap<>();
        yearly.put("lookupCode", "YEARLY");
        yearly.put("meaning", "Yearly");
        billingFrequencies.add(yearly);
        
        Map<String, String> oneTime = new HashMap<>();
        oneTime.put("lookupCode", "ONE_TIME");
        oneTime.put("meaning", "One Time");
        billingFrequencies.add(oneTime);
        
        // Billing Channels
        List<Map<String, String>> billingChannels = new ArrayList<>();
        
        Map<String, String> email = new HashMap<>();
        email.put("lookupCode", "EMAIL");
        email.put("meaning", "Email");
        billingChannels.add(email);
        
        Map<String, String> mail = new HashMap<>();
        mail.put("lookupCode", "MAIL");
        mail.put("meaning", "Mail");
        billingChannels.add(mail);
        
        Map<String, String> portal = new HashMap<>();
        portal.put("lookupCode", "PORTAL");
        portal.put("meaning", "Portal");
        billingChannels.add(portal);
        
        Map<String, String> inPerson = new HashMap<>();
        inPerson.put("lookupCode", "IN_PERSON");
        inPerson.put("meaning", "In Person");
        billingChannels.add(inPerson);
        
        // UOM
        List<Map<String, String>> uomList = new ArrayList<>();
        
        Map<String, String> each = new HashMap<>();
        each.put("lookupCode", "EA");
        each.put("meaning", "Each (EA)");
        uomList.add(each);
        
        Map<String, String> hour = new HashMap<>();
        hour.put("lookupCode", "HR");
        hour.put("meaning", "Hour (HR)");
        uomList.add(hour);
        
        Map<String, String> day = new HashMap<>();
        day.put("lookupCode", "DAY");
        day.put("meaning", "Day (DAY)");
        uomList.add(day);
        
        Map<String, String> month = new HashMap<>();
        month.put("lookupCode", "MTH");
        month.put("meaning", "Month (MTH)");
        uomList.add(month);
        
        Map<String, String> year = new HashMap<>();
        year.put("lookupCode", "YR");
        year.put("meaning", "Year (YR)");
        uomList.add(year);
        
        response.put("status", "success");
        response.put("billingFrequencies", billingFrequencies);
        response.put("billingChannels", billingChannels);
        response.put("uomList", uomList);
        
        return ResponseEntity.ok(response);
    }
}

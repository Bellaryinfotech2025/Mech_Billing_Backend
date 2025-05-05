package com.bellaryinfotech.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.bellaryinfotech.model.OrderFabricationImport;
import com.bellaryinfotech.repo.OrderFabricationImportRepository;
import com.bellaryinfotech.service.ExcelImportService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/V2.0")
@CrossOrigin(origins = "*")  
public class FabricationImportController {

    private static final Logger log = LoggerFactory.getLogger(FabricationImportController.class);

    @Autowired
    private ExcelImportService excelImportService;

    @Autowired
    private OrderFabricationImportRepository repository;

    @PostMapping(value = "/imports", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            // Check if file is empty
            if (file.isEmpty()) {
                log.error("Uploaded file is empty");
                return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "Please select a file to upload"
                ));
            }

            // Check if file is an Excel file
            if (!file.getOriginalFilename().endsWith(".xlsx") && !file.getOriginalFilename().endsWith(".xls")) {
                log.error("Uploaded file is not an Excel file: {}", file.getOriginalFilename());
                return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "Please upload an Excel file (.xlsx or .xls)"
                ));
            }

            log.info("Processing file: {}, size: {} bytes", file.getOriginalFilename(), file.getSize());
        
            // Process the file
            List<OrderFabricationImport> importedRecords = excelImportService.importExcelToDatabase(file);
            int recordsImported = importedRecords.size();

            // Count success and failure records
            int successCount = (int) importedRecords.stream()
                .filter(record -> "SUCCESS".equals(record.getIfaceStatus()))
                .count();
            
            int failureCount = (int) importedRecords.stream()
                .filter(record -> "ERROR".equals(record.getIfaceStatus()))
                .count();
            
            int pendingCount = (int) importedRecords.stream()
                .filter(record -> "PENDING".equals(record.getIfaceStatus()) || "PROCESSING".equals(record.getIfaceStatus()))
                .count();

            log.info("Import completed. Records imported: {} (Success: {}, Failure: {}, Pending: {})", 
                    recordsImported, successCount, failureCount, pendingCount);
        
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "File imported successfully");
            response.put("recordsImported", recordsImported);
            response.put("successCount", successCount);
            response.put("failureCount", failureCount);
            response.put("pendingCount", pendingCount);
            response.put("data", importedRecords); // Return the imported records

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Failed to import data", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                    "status", "error",
                    "message", "Failed to import data: " + e.getMessage(),
                    "details", e.toString()
                ));
        }
    }

    @GetMapping("/imported-data")
    public ResponseEntity<?> getImportedData(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ifaceId") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        try {
            log.info("Fetching imported data: page={}, size={}, sortBy={}, sortDir={}", page, size, sortBy, sortDir);
            
            Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
            Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
            
            Page<OrderFabricationImport> dataPage = repository.findAll(pageable);
            
            // Log the data being returned
            log.info("Found {} records, total {} records", dataPage.getContent().size(), dataPage.getTotalElements());
            
            Map<String, Object> response = new HashMap<>();
            response.put("data", dataPage.getContent());
            response.put("currentPage", dataPage.getNumber());
            response.put("totalItems", dataPage.getTotalElements());
            response.put("totalPages", dataPage.getTotalPages());
            
            // Add import status counts
            long successCount = repository.countByIfaceStatus("SUCCESS");
            long failureCount = repository.countByIfaceStatus("ERROR");
            long pendingCount = repository.countByIfaceStatus("PENDING") + repository.countByIfaceStatus("PROCESSING");
            
            response.put("successCount", successCount);
            response.put("failureCount", failureCount);
            response.put("pendingCount", pendingCount);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Failed to retrieve data", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                        "status", "error",
                        "message", "Failed to retrieve data: " + e.getMessage()
                    ));
        }
    }

    @GetMapping("/latest-imported")
    public ResponseEntity<?> getLatestImportedData() {
        try {
            log.info("Fetching latest imported data");
            
            // Get the most recent records (limit to 100 for performance)
            List<OrderFabricationImport> latestRecords = repository.findTop100ByOrderByIfaceIdDesc();
            
            log.info("Found {} latest records", latestRecords.size());
            
            // Count records by status
            long successCount = latestRecords.stream()
                .filter(record -> "SUCCESS".equals(record.getIfaceStatus()))
                .count();
            
            long failureCount = latestRecords.stream()
                .filter(record -> "ERROR".equals(record.getIfaceStatus()))
                .count();
            
            long pendingCount = latestRecords.stream()
                .filter(record -> "PENDING".equals(record.getIfaceStatus()) || "PROCESSING".equals(record.getIfaceStatus()))
                .count();
            
            Map<String, Object> response = new HashMap<>();
            response.put("data", latestRecords);
            response.put("totalItems", latestRecords.size());
            response.put("successCount", successCount);
            response.put("failureCount", failureCount);
            response.put("pendingCount", pendingCount);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Failed to retrieve latest data", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                        "status", "error",
                        "message", "Failed to retrieve latest data: " + e.getMessage()
                    ));
        }
    }

    @GetMapping("/import-status")
    public ResponseEntity<?> getImportStatus() {
        try {
            log.info("Fetching import status statistics");
            
            // Count records by status
            long successCount = repository.countByIfaceStatus("SUCCESS");
            long failureCount = repository.countByIfaceStatus("ERROR");
            long pendingCount = repository.countByIfaceStatus("PENDING") + repository.countByIfaceStatus("PROCESSING");
            long totalCount = successCount + failureCount + pendingCount;
            
            Map<String, Object> response = new HashMap<>();
            response.put("successCount", successCount);
            response.put("failureCount", failureCount);
            response.put("pendingCount", pendingCount);
            response.put("totalCount", totalCount);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Failed to retrieve import status", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                        "status", "error",
                        "message", "Failed to retrieve import status: " + e.getMessage()
                    ));
        }
    }

    @GetMapping("/template")
    public ResponseEntity<?> getTemplateInfo() {
        try {
            // This endpoint helps users understand what columns are expected
            Map<String, Object> templateInfo = new HashMap<>();
            
            // First sheet columns
            Map<String, String> sheet1Columns = new HashMap<>();
            sheet1Columns.put("ORDER_NUMBER", "Order number (e.g., ORD_321)");
            sheet1Columns.put("ORIG_LINE_NO", "Original line number (e.g., 1)");
            sheet1Columns.put("LINE_NO", "Line number (e.g., 1.1)");
            sheet1Columns.put("DRAWING NO", "Drawing number (e.g., MEC-11-12-Q7NX-DE-41-17362)");
            sheet1Columns.put("DESCRIPTION", "Description of the drawing");
            
            // Second sheet columns
            Map<String, String> sheet2Columns = new HashMap<>();
            sheet2Columns.put("ERECTION MKD.", "Erection marked number (e.g., A-11)");
            sheet2Columns.put("ITEM NO.", "Item number (e.g., 1)");
            sheet2Columns.put("SECTION", "Section information (e.g., PL.300x32)");
            sheet2Columns.put("LENGTH", "Length value (numeric)");
            sheet2Columns.put("qty", "Quantity (numeric)");
            sheet2Columns.put("UNIT", "Unit (numeric)");
            sheet2Columns.put("TOTAL WT.", "Total weight (numeric)");
            sheet2Columns.put("QTY. REQD", "Quantity required (numeric)");
            sheet2Columns.put("EREC. MKD. WT.", "Erection marked weight (numeric)");
            sheet2Columns.put("REMARKS", "Remarks or notes (e.g., Completed)");
            
            templateInfo.put("sheet1", sheet1Columns);
            templateInfo.put("sheet2", sheet2Columns);
            templateInfo.put("instructions", "The Excel file should have two sheets with the columns listed above. " +
                    "The system will match records between sheets based on order number and line number.");
            
            return ResponseEntity.ok(templateInfo);
        } catch (Exception e) {
            log.error("Failed to retrieve template info", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                        "status", "error",
                        "message", "Failed to retrieve template info: " + e.getMessage()
                    ));
        }
    }
    //fabrication get api
    @GetMapping("/fabrication-columns")
    public ResponseEntity<?> getFabricationColumns() {
        try {
            log.info("Fetching fabrication table columns");
            
            // Define the columns structure that matches the frontend expectations
            List<Map<String, Object>> columns = new ArrayList<>();
            
            // Order Number column
            Map<String, Object> orderNumberCol = new HashMap<>();
            orderNumberCol.put("id", "orderNumber");
            orderNumberCol.put("label", "Order Number");
            orderNumberCol.put("width", "150px");
            orderNumberCol.put("placeholder", "Enter order");
            orderNumberCol.put("hasIcon", true);
            columns.add(orderNumberCol);
            
            // Original Line No column
            Map<String, Object> origLineNoCol = new HashMap<>();
            origLineNoCol.put("id", "origLineNo");
            origLineNoCol.put("label", "Original Line No");
            origLineNoCol.put("width", "120px");
            origLineNoCol.put("placeholder", "Enter original line");
            origLineNoCol.put("hasIcon", true);
            columns.add(origLineNoCol);
            
            // Line No column
            Map<String, Object> lineNoCol = new HashMap<>();
            lineNoCol.put("id", "lineNo");
            lineNoCol.put("label", "Line No");
            lineNoCol.put("width", "100px");
            lineNoCol.put("placeholder", "Enter line");
            columns.add(lineNoCol);
            
            // Drawing No column
            Map<String, Object> drawingNoCol = new HashMap<>();
            drawingNoCol.put("id", "drawingNo");
            drawingNoCol.put("label", "Drawing No");
            drawingNoCol.put("width", "180px");
            drawingNoCol.put("placeholder", "Enter drawing No");
            drawingNoCol.put("hasIcon", true);
            columns.add(drawingNoCol);
            
            // Description column
            Map<String, Object> descriptionCol = new HashMap<>();
            descriptionCol.put("id", "description");
            descriptionCol.put("label", "Description");
            descriptionCol.put("width", "250px");
            descriptionCol.put("placeholder", "Enter description");
            columns.add(descriptionCol);
            
            // Erection Mkd column
            Map<String, Object> erectionMkdCol = new HashMap<>();
            erectionMkdCol.put("id", "erectionMkd");
            erectionMkdCol.put("label", "Erection Mkd");
            erectionMkdCol.put("width", "150px");
            erectionMkdCol.put("placeholder", "Enter marked");
            columns.add(erectionMkdCol);
            
            // Item No column
            Map<String, Object> itemNoCol = new HashMap<>();
            itemNoCol.put("id", "itemNo");
            itemNoCol.put("label", "Item No");
            itemNoCol.put("width", "100px");
            itemNoCol.put("placeholder", "Enter item");
            columns.add(itemNoCol);
            
            // Section column
            Map<String, Object> sectionCol = new HashMap<>();
            sectionCol.put("id", "section");
            sectionCol.put("label", "Section");
            sectionCol.put("width", "150px");
            sectionCol.put("placeholder", "Enter section");
            columns.add(sectionCol);
            
            // Length column
            Map<String, Object> lengthCol = new HashMap<>();
            lengthCol.put("id", "length");
            lengthCol.put("label", "Length");
            lengthCol.put("width", "100px");
            lengthCol.put("placeholder", "Enter length");
            columns.add(lengthCol);
            
            // Qty column
            Map<String, Object> qtyCol = new HashMap<>();
            qtyCol.put("id", "qty");
            qtyCol.put("label", "Qty");
            qtyCol.put("width", "80px");
            qtyCol.put("placeholder", "Enter qty");
            columns.add(qtyCol);
            
            // Unit column
            Map<String, Object> unitCol = new HashMap<>();
            unitCol.put("id", "unit");
            unitCol.put("label", "Unit");
            unitCol.put("width", "80px");
            unitCol.put("placeholder", "Enter unit");
            columns.add(unitCol);
            
            // Total Wt column
            Map<String, Object> totalWtCol = new HashMap<>();
            totalWtCol.put("id", "totalWt");
            totalWtCol.put("label", "Total Wt");
            totalWtCol.put("width", "120px");
            totalWtCol.put("placeholder", "Enter weight");
            columns.add(totalWtCol);
            
            // Qty Reqd column
            Map<String, Object> qtyReqdCol = new HashMap<>();
            qtyReqdCol.put("id", "qtyReqd");
            qtyReqdCol.put("label", "Qty Reqd");
            qtyReqdCol.put("width", "120px");
            qtyReqdCol.put("placeholder", "Enter qty req.");
            columns.add(qtyReqdCol);
            
            // Erec Mkd Wt column
            Map<String, Object> erecMkdWtCol = new HashMap<>();
            erecMkdWtCol.put("id", "erecMkdWt");
            erecMkdWtCol.put("label", "Erec Mkd Wt");
            erecMkdWtCol.put("width", "150px");
            erecMkdWtCol.put("placeholder", "Enter weight");
            columns.add(erecMkdWtCol);
            
            // Remarks column
            Map<String, Object> remarksCol = new HashMap<>();
            remarksCol.put("id", "remarks");
            remarksCol.put("label", "Remarks");
            remarksCol.put("width", "150px");
            remarksCol.put("placeholder", "Enter remarks");
            columns.add(remarksCol);
            
            // Status column
            Map<String, Object> statusCol = new HashMap<>();
            statusCol.put("id", "status");
            statusCol.put("label", "Status");
            statusCol.put("width", "120px");
            statusCol.put("placeholder", "Status");
            statusCol.put("isStatus", true);
            columns.add(statusCol);
            
            return ResponseEntity.ok(columns);
        } catch (Exception e) {
            log.error("Failed to retrieve fabrication columns", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                        "status", "error",
                        "message", "Failed to retrieve fabrication columns: " + e.getMessage()
                    ));
        }
    }
}
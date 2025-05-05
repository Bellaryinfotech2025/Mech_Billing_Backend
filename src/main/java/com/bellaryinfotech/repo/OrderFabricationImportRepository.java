package com.bellaryinfotech.repo;

import com.bellaryinfotech.model.OrderFabricationImport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderFabricationImportRepository extends JpaRepository<OrderFabricationImport, Long> {
    
    // Method to count records by status
    @Query("SELECT COUNT(o) FROM OrderFabricationImport o WHERE o.ifaceStatus = :status")
    long countByIfaceStatus(@Param("status") String status);
    
    // Method to find the latest imported records
    List<OrderFabricationImport> findTop100ByOrderByIfaceIdDesc();
    
    // Find by batch name
    List<OrderFabricationImport> findByBatchName(String batchName);
    
    // Find by order number
    List<OrderFabricationImport> findByOrderNumber(String orderNumber);
    
    // Find by drawing number
    List<OrderFabricationImport> findByDrawingNo(String drawingNo);
    
    // Find by building name
    List<OrderFabricationImport> findByBuildingName(String buildingName);
    
    // Find by status
    List<OrderFabricationImport> findByIfaceStatus(String ifaceStatus);
    
    // Find by status with pagination
    Page<OrderFabricationImport> findByIfaceStatus(String ifaceStatus, Pageable pageable);
    
    // Custom query to search across multiple fields
    @Query("SELECT o FROM OrderFabricationImport o WHERE " +
           "(:search IS NULL OR " +
           "LOWER(o.orderNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(o.drawingNo) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(o.buildingName) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<OrderFabricationImport> findBySearchTerm(@Param("search") String search, Pageable pageable);
}
package com.bellaryinfotech.repo;
 
import com.bellaryinfotech.model.OrderFabricationDetail;
 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderFabricationDetailRepository extends JpaRepository<OrderFabricationDetail, Long> {
    
    List<OrderFabricationDetail> findByOrderId(Long orderId);
    
    List<OrderFabricationDetail> findByBuildingNameContainingIgnoreCase(String buildingName);
    
    List<OrderFabricationDetail> findByDrawingNoContainingIgnoreCase(String drawingNo);
    
    List<OrderFabricationDetail> findByOrderNumber(String orderNumber);
    
    List<OrderFabricationDetail> findByLineId(Long lineId);
    
    List<OrderFabricationDetail> findByErectionMkdContainingIgnoreCase(String erectionMkd);
    
    List<OrderFabricationDetail> findByLineIdAndErectionMkdContainingIgnoreCase(Long lineId, String erectionMkd);
    
    List<OrderFabricationDetail> findByErectionMkd(String erectionMkd);
}
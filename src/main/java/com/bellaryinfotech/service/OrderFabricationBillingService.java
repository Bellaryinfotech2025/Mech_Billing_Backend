package com.bellaryinfotech.service;

import com.bellaryinfotech.DTO.OrderFabricationBillingDTO;
import java.util.List;

public interface OrderFabricationBillingService {
    List<OrderFabricationBillingDTO> getAllBillings();
    List<OrderFabricationBillingDTO> storeSelectedErectionMkds(List<String> erectionMkds);
}

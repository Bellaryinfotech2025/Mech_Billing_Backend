package com.bellaryinfotech.service;

import com.bellaryinfotech.DTO.OrderFabricationAlignmentDTO;
import java.util.List;

public interface OrderFabricationAlignmentService {
    List<OrderFabricationAlignmentDTO> getAllAlignments();
    List<OrderFabricationAlignmentDTO> storeSelectedErectionMkds(List<String> erectionMkds);
}

package com.bellaryinfotech.service;

import com.bellaryinfotech.DAO.OrderFabricationAlignmentDao;
import com.bellaryinfotech.DTO.OrderFabricationAlignmentDTO;
import com.bellaryinfotech.DTO.OrderFabricationAlignmentDTO;
import com.bellaryinfotech.DTO.OrderFabricationAlignmentDTOImpl;
import com.bellaryinfotech.model.OrderFabricationAlignment;
import com.bellaryinfotech.model.OrderFabricationErection;
import com.bellaryinfotech.repo.OrderFabricationErectionRepository;
import com.bellaryinfotech.service.OrderFabricationAlignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderFabricationAlignmentServiceImpl implements OrderFabricationAlignmentService {

    @Autowired
    private OrderFabricationAlignmentDao alignmentDao;

    @Autowired
    private OrderFabricationErectionRepository erectionRepository;

    @Override
    public List<OrderFabricationAlignmentDTO> getAllAlignments() {
        List<OrderFabricationAlignment> alignments = alignmentDao.findAll();
        return OrderFabricationAlignmentDTOImpl.toDTOList(alignments);
    }

    @Override
    @Transactional
    public List<OrderFabricationAlignmentDTO> storeSelectedErectionMkds(List<String> erectionMkds) {
        List<OrderFabricationAlignment> alignmentsToSave = new ArrayList<>();
        List<OrderFabricationErection> erections = erectionRepository.findByErectionMkdIn(erectionMkds);

        for (OrderFabricationErection erection : erections) {
            OrderFabricationAlignment alignment = new OrderFabricationAlignment();
            alignment.setBuildingName(erection.getBuildingName());
            alignment.setDrawingNo(erection.getDrawingNo());
            alignment.setDrawingDescription(erection.getDrawingDescription());
            alignment.setOrderNumber(erection.getOrderNumber());
            alignment.setOrderId(erection.getOrderId());
            alignment.setOrigLineNumber(erection.getOrigLineNumber());
            alignment.setOrigLineId(erection.getOrigLineId());
            alignment.setLineNumber(erection.getLineNumber());
            alignment.setLineId(erection.getLineId());
            alignment.setErectionMkd(erection.getErectionMkd());
            alignment.setItemNo(erection.getItemNo());
            alignment.setSection(erection.getSection());
            alignment.setLength(erection.getLength());
            alignment.setLengthUom(erection.getLengthUom());
            alignment.setQuantity(erection.getQuantity());
            alignment.setUnitPrice(erection.getUnitPrice());
            alignment.setUnitPriceUom(erection.getUnitPriceUom());
            alignment.setTotalQuantity(erection.getTotalQuantity());
            alignment.setTotalQuantityUom(erection.getTotalQuantityUom());
            alignment.setOriginalQuantity(erection.getOriginalQuantity());
            alignment.setRepeatedQty(erection.getRepeatedQty());
            alignment.setRemark(erection.getRemark());
            alignment.setTenantId(erection.getTenantId());
            alignment.setCreationDate(erection.getCreationDate());
            alignment.setCreatedBy(erection.getCreatedBy());
            alignment.setLastUpdateDate(erection.getLastUpdateDate());
            alignment.setLastUpdatedBy(erection.getLastUpdatedBy());
            alignment.setOrgId(erection.getOrgId());
            alignment.setCreatedDate(erection.getCreatedDate());
            alignment.setUpdatedBy(erection.getUpdatedBy());
            alignment.setUpdatedDate(erection.getUpdatedDate());
            alignment.setVersion(erection.getVersion());
            alignment.setStatus("alignment"); // Set status to alignment
            alignmentsToSave.add(alignment);
        }
        List<OrderFabricationAlignment> saved = alignmentDao.saveAll(alignmentsToSave);
        return OrderFabricationAlignmentDTOImpl.toDTOList(saved);
    }
}

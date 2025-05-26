package com.bellaryinfotech.service;

import com.bellaryinfotech.DAO.OrderFabricationBillingDao;
import com.bellaryinfotech.DTO.OrderFabricationBillingDTO;
import com.bellaryinfotech.DTO.OrderFabricationBillingDTOImpl;
import com.bellaryinfotech.model.OrderFabricationAlignment;
import com.bellaryinfotech.model.OrderFabricationBilling;
import com.bellaryinfotech.repo.OrderFabricationAlignmentRepository;
import com.bellaryinfotech.service.OrderFabricationBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderFabricationBillingServiceImpl implements OrderFabricationBillingService {

    @Autowired
    private OrderFabricationBillingDao billingDao;

    @Autowired
    private OrderFabricationAlignmentRepository alignmentRepository;

    @Override
    public List<OrderFabricationBillingDTO> getAllBillings() {
        List<OrderFabricationBilling> billings = billingDao.findAll();
        return OrderFabricationBillingDTOImpl.toDTOList(billings);
    }

    @Override
    @Transactional
    public List<OrderFabricationBillingDTO> storeSelectedErectionMkds(List<String> erectionMkds) {
        List<OrderFabricationAlignment> alignments = alignmentRepository.findByErectionMkdIn(erectionMkds);
        List<OrderFabricationBilling> billingsToSave = new ArrayList<>();
        for (OrderFabricationAlignment alignment : alignments) {
            OrderFabricationBilling billing = OrderFabricationBillingDTOImpl.fromAlignment(alignment);
            billingsToSave.add(billing);
        }
        List<OrderFabricationBilling> saved = billingDao.saveAll(billingsToSave);
        return OrderFabricationBillingDTOImpl.toDTOList(saved);
    }
}

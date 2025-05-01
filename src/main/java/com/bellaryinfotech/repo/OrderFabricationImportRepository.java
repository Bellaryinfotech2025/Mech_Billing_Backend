package com.bellaryinfotech.repo;

import com.bellaryinfotech.model.OrderFabricationImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderFabricationImportRepository extends JpaRepository<OrderFabricationImport, Long> {
    // Add a method to find the most recent records
    List<OrderFabricationImport> findTop100ByOrderByIfaceIdDesc();
}


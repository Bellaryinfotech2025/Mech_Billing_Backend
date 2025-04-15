package com.bellaryinfotech.repo;

import com.bellaryinfotech.model.LinesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinesRepository extends JpaRepository<LinesModel, Long> {
    
    List<LinesModel> findByOrderId(Long orderId);
    
    @Query("SELECT MAX(o.lineNumber) FROM LinesModel o WHERE o.orderId = ?1")
    Integer findMaxLineNumberByOrderId(Long orderId);
    
    List<LinesModel> findByParentLineNumber(Integer parentLineNumber);
}

package com.bellaryinfotech.repo;

import com.bellaryinfotech.model.OrderFabricationAlignment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFabricationAlignmentRepository extends JpaRepository<OrderFabricationAlignment, Long> {

	List<OrderFabricationAlignment> findByErectionMkdIn(List<String> erectionMkds);
}

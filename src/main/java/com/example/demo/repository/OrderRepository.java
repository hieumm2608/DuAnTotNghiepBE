package com.example.demo.repository;

import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.ShiftEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {

    List<OrderEntity> findByShiftEntity(ShiftEntity shiftEntity);
}

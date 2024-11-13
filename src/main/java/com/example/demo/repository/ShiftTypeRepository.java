package com.example.demo.repository;

import com.example.demo.entity.ShiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ShiftTypeRepository extends JpaRepository<ShiftType, Integer> {
}

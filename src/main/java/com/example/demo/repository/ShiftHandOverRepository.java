package com.example.demo.repository;

import com.example.demo.entity.ShiftHandover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShiftHandOverRepository extends JpaRepository<ShiftHandover,Integer> {

    Optional<ShiftHandover> findByShiftEntity_IdShift(int idShift);
}

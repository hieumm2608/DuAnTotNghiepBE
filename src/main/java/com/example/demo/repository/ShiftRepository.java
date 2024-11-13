package com.example.demo.repository;

import com.example.demo.entity.ShiftEntity;
import com.example.demo.request.ShiftRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;


@Repository
public interface ShiftRepository extends JpaRepository<ShiftEntity,Integer> {
    @Query("SELECT s FROM ShiftEntity s WHERE s.isWorking = :isWorking AND s.shiftType.idShiftType = :idShiftType")
    Optional<ShiftEntity> findByIsWorkingAndShiftType_IdShiftType(@Param("isWorking") boolean isWorking, @Param("idShiftType") int idShiftType);}

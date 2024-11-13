package com.example.demo.service;

import com.example.demo.entity.ShiftEntity;
import com.example.demo.entity.ShiftHandover;
import com.example.demo.request.ShiftHandOverRequestDTO;
import com.example.demo.request.ShiftRequestDTO;
import com.example.demo.respone.ApiRespone;
import com.example.demo.respone.ShiftResponseDTO;
import org.springframework.stereotype.Service;


public interface ShiftService {
    ShiftEntity updateShift(ShiftRequestDTO shiftRequestDTO);
    ShiftEntity createShift(ShiftRequestDTO shiftRequestDTO);
    ShiftHandover updateShiftHandover(int id, ShiftEntity shiftEntity);
    ShiftHandover createShiftHandover(ShiftEntity shiftEntity,double cashStart);
    ShiftHandover updateShiftHandover(ShiftHandOverRequestDTO shiftHandOverRequestDTO);
    ApiRespone<ShiftResponseDTO> toResponse (ShiftEntity shiftEntity,ShiftHandover shiftHandover);
}

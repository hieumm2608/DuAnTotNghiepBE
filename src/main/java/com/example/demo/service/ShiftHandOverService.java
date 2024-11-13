package com.example.demo.service;

import com.example.demo.entity.ShiftEntity;
import com.example.demo.entity.ShiftHandover;
import com.example.demo.request.ShiftHandOverRequestDTO;

public interface ShiftHandOverService {
    ShiftHandover createShiftHandover(ShiftEntity shiftEntity, ShiftHandOverRequestDTO shiftHandOverRequestDTO,ShiftHandover shiftHandover);

}

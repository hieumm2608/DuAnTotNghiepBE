package com.example.demo.respone;

import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.ShiftHandover;
import com.example.demo.entity.ShiftType;
import com.example.demo.entity.UserEnitty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShiftResponseDTO {
    private int idShift;
    private String idUser;
    private ShiftType shiftType;
    private List<OrderEntity> listOrder;
    private Boolean isWorking;
    private int idShiftHandOver;
    private double bankAmount;
    private double cashAmount;
    private double addtionalCost;
}

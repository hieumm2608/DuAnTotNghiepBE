package com.example.demo.request;

import com.example.demo.entity.OrderEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShiftHandOverRequestDTO {
    @NotNull(message = "ID_USER_NOT_NULL")
    private String idUser;
    private List<OrderEntity> listOrder;
    private Boolean isWorking;
    private double bankAmount;
    private double cashAmount;
    private double addtionalCost;
}

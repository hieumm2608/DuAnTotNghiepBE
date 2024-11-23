package com.example.demo.request;


import com.example.demo.respone.OrderResponeDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShiftRequestDTO {
        @NotNull(message = "ID_USER_NOT_NULL")
        private String idUser;
        @NotNull(message = "ID_SHIFT_NOT_NULL")
        private int idShiftType;

        private List<OrderResponeDTO> listOrder;
        private Double cashStart;
        private Boolean isWorking;




}

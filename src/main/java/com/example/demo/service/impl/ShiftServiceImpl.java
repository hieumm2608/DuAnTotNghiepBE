package com.example.demo.service.impl;

import com.example.demo.entity.ShiftEntity;
import com.example.demo.entity.ShiftHandover;
import com.example.demo.entity.ShiftType;
import com.example.demo.entity.UserEnitty;
import com.example.demo.enums.ErrorEnum;
import com.example.demo.repository.ShiftHandOverRepository;
import com.example.demo.repository.ShiftRepository;
import com.example.demo.repository.ShiftTypeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.ShiftHandOverRequestDTO;
import com.example.demo.request.ShiftRequestDTO;
import com.example.demo.respone.ApiRespone;
import com.example.demo.respone.ShiftResponseDTO;
import com.example.demo.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service
public class ShiftServiceImpl implements ShiftService {
    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private ShiftTypeRepository shiftTypeRepository ;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShiftHandOverRepository shiftHandOverRepository;


    @Override
    public ShiftEntity updateShift(ShiftRequestDTO shiftRequestDTO) {
        ShiftEntity entity = new ShiftEntity();
        //      find shift type

        return entity;
    }

    @Override
    public ShiftEntity createShift(ShiftRequestDTO shiftRequestDTO) {
        ShiftEntity entity = new ShiftEntity();
//      find shift type
        ShiftType shiftType = shiftTypeRepository.findById(shiftRequestDTO.getIdShiftType()).orElseThrow(
                () -> new RuntimeException(ErrorEnum.id_Shift_not_null.getMessage()));
//      find User
        UserEnitty userEnitty = userRepository.findById(UUID.fromString(shiftRequestDTO.getIdUser())).orElseThrow(
                () -> new RuntimeException(ErrorEnum.user_not_exist.getMessage()));
        entity.setShiftType(shiftType);
        entity.setUserEnitty(userEnitty);
        entity.setDateCreate(new Date());
        entity.setIsWorking(true);
//      add từ cookie
        entity.setOrderEntities(null);
        return entity;
    }

    @Override
    public ShiftHandover updateShiftHandover(int id, ShiftEntity shiftEntity) {
        ShiftHandover shiftHandover = new ShiftHandover();
        shiftHandover.setShiftEntity(shiftEntity);
        return shiftHandover;
    }

    @Override
    public ShiftHandover createShiftHandover(ShiftEntity shiftEntity, double cashStart) {
        ShiftHandover shiftHandover = new ShiftHandover();
        shiftHandover.setShiftEntity(shiftEntity);
        shiftHandover.setCashAtStart(cashStart);
        shiftEntity.setShiftHandover(shiftHandover);
        try {
            shiftHandOverRepository.save(shiftHandover);
        }catch (Exception e){
            e.printStackTrace();
        }
        return shiftHandover;
    }

    @Override
    public ShiftHandover updateShiftHandover(ShiftHandOverRequestDTO shiftHandOverDTO) {
        ShiftHandover shiftHandover = new ShiftHandover();
        shiftHandover.setCashAmount(shiftHandOverDTO.getCashAmount());
        shiftHandover.setBankAmount(shiftHandOverDTO.getBankAmount());
        return shiftHandover;
    }

    @Override
    public ApiRespone<ShiftResponseDTO> toResponse(ShiftEntity shiftEntity, ShiftHandover shiftHandover) {
        ShiftResponseDTO shiftResponseDTO = new ShiftResponseDTO();

        shiftResponseDTO.setIdShift(shiftEntity.getIdShift());
        shiftResponseDTO.setIdUser(shiftEntity.getUserEnitty().getIdUser().toString());
        shiftResponseDTO.setIdShiftHandOver(shiftHandover.getIdShiftHandover());
        shiftResponseDTO.setListOrder(shiftEntity.getOrderEntities());
        shiftResponseDTO.setIsWorking(shiftEntity.getIsWorking());
        shiftResponseDTO.setCashAmount(shiftHandover.getCashAmount() == null ? 0 : shiftHandover.getCashAmount() );
        shiftResponseDTO.setBankAmount(shiftHandover.getBankAmount() == null ? 0 : shiftHandover.getBankAmount() );
        shiftResponseDTO.setAddtionalCost(shiftHandover.getAdditionalCost() == null ? 0 : shiftHandover.getAdditionalCost() );
        shiftResponseDTO.setShiftType(shiftEntity.getShiftType());

        ApiRespone<ShiftResponseDTO> apiRespone = new ApiRespone<>();
        apiRespone.setResult(shiftResponseDTO);
        return apiRespone;
    }


}

package com.example.demo.service.impl;

import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.ShiftEntity;
import com.example.demo.entity.ShiftHandover;
import com.example.demo.repository.ShiftHandOverRepository;
import com.example.demo.repository.ShiftRepository;
import com.example.demo.request.ShiftHandOverRequestDTO;
import com.example.demo.service.ShiftHandOverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftHandOverServiceImpl  implements ShiftHandOverService {

    @Autowired
    private final ShiftRepository shiftRepository;
    @Autowired
    private ShiftHandOverRepository shiftHandOverRepository;

    public ShiftHandOverServiceImpl(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    @Override
    public ShiftHandover createShiftHandover(ShiftEntity shiftEntity
            ,ShiftHandOverRequestDTO shiftHandOverRequestDTO
            ,ShiftHandover shiftHandover) {
        shiftHandover.setBankAmount(shiftHandOverRequestDTO.getBankAmount());
        shiftHandover.setCashAmount(shiftHandOverRequestDTO.getCashAmount());
        shiftHandover.setAdditionalCost(shiftHandOverRequestDTO.getAddtionalCost());
        shiftEntity.setOrderEntities(shiftHandOverRequestDTO.getListOrder());
        shiftEntity.setIsWorking(shiftHandOverRequestDTO.getIsWorking());
        double total = shiftHandOverRequestDTO.getListOrder()
                .stream()
                .mapToDouble(OrderEntity::getTotal)
                .sum();

        System.out.println(total);
        shiftHandover.setShiftRevenue(total);
        double totalAmount = shiftHandover.getShiftRevenue() - shiftHandover.getAdditionalCost();
        shiftHandover.setTotalAmount(totalAmount);

        try {
            shiftHandOverRepository.save(shiftHandover);

        }catch (Exception e){
            e.printStackTrace();
        }
        return shiftHandover;
    }



}

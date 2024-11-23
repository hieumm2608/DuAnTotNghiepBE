package com.example.demo.controller.user;

import com.example.demo.entity.*;
import com.example.demo.enums.ErrorEnum;
import com.example.demo.repository.ShiftHandOverRepository;
import com.example.demo.repository.ShiftRepository;
import com.example.demo.repository.ShiftTypeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.ShiftHandOverRequestDTO;
import com.example.demo.request.ShiftRequestDTO;
import com.example.demo.respone.ApiRespone;
import com.example.demo.respone.ShiftResponseDTO;
import com.example.demo.service.impl.ShiftHandOverServiceImpl;
import com.example.demo.service.impl.ShiftServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shift")
public class ShiftController {


    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private ShiftTypeRepository shiftTypeRepository ;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShiftHandOverRepository shiftHandOverRepository;

    @Autowired
    private ShiftServiceImpl  shiftServiceimpl;
    @Autowired
    private ShiftHandOverServiceImpl shiftHandOverServiceImpl;


//    url = http://localhost:8080/api/v1/shift?isEnding=true&idShiftType=1
    @GetMapping
    public ApiRespone<ShiftResponseDTO> getShiftByDateShifttype(
            @RequestParam("isEnding") boolean isWorking,
            @RequestParam("idShiftType") int idShiftType
    )
    {
        ShiftEntity shiftEntity = shiftRepository.findByIsWorkingAndShiftType_IdShiftType(isWorking, idShiftType).orElse(null);

        if (shiftEntity == null) {
            throw new RuntimeException(ErrorEnum.id_shift_not_exist.getMessage());

        }
        ShiftHandover shiftHandover = shiftHandOverRepository.findByShiftEntity_IdShift(shiftEntity.getIdShift()).orElse( null);
        if (shiftHandover == null) {
            throw new RuntimeException(ErrorEnum.id_shift_handOver_not_exist.getMessage());
        }

        try {
            ApiRespone<ShiftResponseDTO> entity = shiftServiceimpl.toResponse(shiftEntity, shiftHandover);
            return  entity;
        }catch(Exception e){
            return null;
        }


    }

//    @GetMapping
//    List<ShiftEntity> shiftEntityList(){
//        return shiftRepository.findAll();
//    }



    @PostMapping()
    public ApiRespone<ShiftEntity> createShiftEntity(@RequestBody ShiftRequestDTO shiftRequestDTO) {
        ApiRespone<ShiftEntity> api = new ApiRespone<>();
        ShiftEntity entity = shiftServiceimpl.createShift(shiftRequestDTO);
        ShiftHandover handover = shiftServiceimpl.createShiftHandover(entity,shiftRequestDTO.getCashStart());
        entity.setShiftHandover(handover);

        try {
            shiftRepository.save(entity);
            shiftHandOverRepository.save(handover);

        }catch (Exception e){
            e.printStackTrace();
        }
        api.setResult(entity);
        System.out.println(api.getResult());
        return api;
    }

//    Json createShiftEntity
//{
//    "dateCreate": "2024-11-11T16:34:50.234+00:00",
//        "dateModify": "2024-11-11T16:34:50.238+00:00",
//        "idShift": 14,
//        "shiftType": {
//    "startime": "11:01:00",
//            "endtime": "14:00:00"
//},
//    "userEnitty": {
//    "idUser": "32000000-0000-0000-0000-000000000000",
//            "fullname": "user02",
//            "username": "user02",
//            "password": "12345",
//            "deleted": false,
//            "admin": true
//},
//    "isWorking": true
//}

    @PostMapping("/{id}")
    public ApiRespone<ShiftEntity> Update(@PathVariable("id") Integer idShift,
                              @RequestBody ShiftHandOverRequestDTO shiftHandOverRequestDTO) {
//        find shift byID
        ShiftEntity entity = shiftRepository.findById(idShift).orElse(null);
        entity.setIsWorking(false);
        if (entity == null) {
            throw new RuntimeException(ErrorEnum.id_shift_not_exist.getMessage());
        }
       ShiftHandover shiftHandover = shiftHandOverRepository.findByShiftEntity_IdShift(idShift).orElse(null);

        if (shiftHandover == null) {
            throw new RuntimeException(ErrorEnum.id_shift_handOver_not_exist.getMessage() );
        }

        ApiRespone<ShiftEntity> api = new ApiRespone<ShiftEntity>();

        try {
            shiftRepository.save(entity);
            shiftHandOverServiceImpl.createShiftHandover(entity,shiftHandOverRequestDTO,shiftHandover);
        }catch (Exception e){
            e.printStackTrace();
        }
        api.setResult(entity);
        return api;
//        json
//        {
//            "idUser": "32000000-0000-0000-0000-000000000000",
//                "listOrder": [
//            {
//                "orderId": "ORD001",
//                    "product": "Coffee",
//                    "quantity": 2,
//                    "price": 6.0
//            },
//            {
//                "orderId": "ORD002",
//                    "product": "Sandwich",
//                    "quantity": 1,
//                    "price": 7.0
//            }
//  ],
//            "isWorking": false,
//                "bankAmount": 15.00,
//                "cashAmount": 22.00,
//                "addtionalCost": 10.50
//        }
    }



















}

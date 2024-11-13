package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class ShiftHandover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Integer idShiftHandover;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="id_shift")
    ShiftEntity shiftEntity;

//    tiền nhận đầu ca
    private Double cashAtStart;

//    tiền mặt hiện tại
    private Double cashAmount;


    private Double bankAmount;

    private Double shiftRevenue;

    private Double additionalCost;

    private Double totalAmount;

}

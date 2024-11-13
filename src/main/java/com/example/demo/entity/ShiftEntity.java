package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
public class ShiftEntity extends  BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idShift;

    @ManyToOne
    @JoinColumn(name= "id_shift_type")
    private ShiftType shiftType;



    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEnitty userEnitty;

    @OneToOne
    @JoinColumn(name= "id_shift_handover")
    @JsonIgnore
    private ShiftHandover shiftHandover;

    @OneToMany(mappedBy = "shiftEntity")
    @JsonIgnore
    private List<OrderEntity> orderEntities;


    private Boolean isWorking = true;




}

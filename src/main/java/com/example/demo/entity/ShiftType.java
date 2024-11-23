package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShiftType {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idShiftType;

    private String nameShift;

    private LocalTime starTime;
    private LocalTime endTime;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");


    public LocalTime getStartime() {
        return starTime;
    }

    public void setStartime(LocalTime startime) {

        startime = LocalTime.parse(startime.format(formatter), formatter);
    }

    public LocalTime getEndtime() {

        return endTime;
    }

    public void setEndtime(LocalTime endtime) {
        endtime = LocalTime.parse(endtime.format(formatter), formatter);
    }
}

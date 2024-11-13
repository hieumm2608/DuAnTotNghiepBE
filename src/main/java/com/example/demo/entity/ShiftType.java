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

    private LocalTime Startime;
    private LocalTime Endtime;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public LocalTime getStartime() {
        return Startime;
    }

    public void setStartime(LocalTime startime) {

        Startime = LocalTime.parse(startime.format(formatter), formatter);
    }

    public LocalTime getEndtime() {
        return Endtime;
    }

    public void setEndtime(LocalTime endtime) {
        Endtime = LocalTime.parse(endtime.format(formatter), formatter);
    }
}

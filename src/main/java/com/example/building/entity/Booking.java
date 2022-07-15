package com.example.building.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.building.common.base.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The entity for Room.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOKING")
public class Booking extends BaseEntity {

    @Column(name = "BOOKING_CD")
    private String bookingCd;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROOM_CD", referencedColumnName = "ROOM_CD")
    private Room roomCd;

    @Column(name = "START_DT")
    private LocalDate startDt;

    @Column(name = "START_TIME")
    private LocalTime startTime;

    @Column(name = "STOP_DT")
    private LocalDate endDt;

    @Column(name = "STOP_TIME")
    private LocalTime stopTime;

    @Column(name = "BOOKING_STS")
    private String bookingSts;
}

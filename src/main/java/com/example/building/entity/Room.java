package com.example.building.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "ROOM")
public class Room extends BaseEntity {

    @Column(name = "ROOM_CD")
    private String roomCd;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "MIN_SIZE")
    private Integer minSize;

    @Column(name = "MAX_SIZE")
    private Integer maxSize;

    @Column(name = "AVATAR")
    private String avatar;
}

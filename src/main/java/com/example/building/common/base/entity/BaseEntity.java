package com.example.building.common.base.entity;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The base entity for Entity class.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer id;

    /** createdDt */
    @Column(name = "CREATED_DT")
    @CreationTimestamp
    public LocalDateTime createdDt;

    /** createdBy */
    @Column(name = "CREATED_BY")
    @CreatedBy
    public String createdBy;

    /** updateDt */
    @Column(name = "UPDATE_DT")
    @LastModifiedDate
    public LocalDateTime updateDt;

    /** updateBy */
    @Column(name = "UPDATE_BY")
    @LastModifiedBy
    public String updateBy;

}

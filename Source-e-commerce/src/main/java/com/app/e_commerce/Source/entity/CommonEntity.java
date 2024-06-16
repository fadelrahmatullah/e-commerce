package com.app.e_commerce.Source.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
@MappedSuperclass
public class CommonEntity {
    @Column(name = "CREATED_BY")
    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
    @Column(name = "CREATED_DT")
    private Date createdDt;

    @Column(name = "CHANGED_BY")
    private String changedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
    @Column(name = "CHANGED_DT")
    private Date changedDt;

}

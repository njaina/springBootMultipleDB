package com.example.prog4.cnaps.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "\"cnaps_employee\"")
public class CnapsEmployee implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;
    @Column(name = "cnaps_number")
    private String cnapsNumber;
    @Column(name = "end_to_end_id", unique = true)
    private String endToEndId;
}

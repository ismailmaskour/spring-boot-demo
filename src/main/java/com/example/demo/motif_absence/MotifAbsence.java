package com.example.demo.motif_absence;

import com.example.demo.utils.Constant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MotifAbsence")
@Table(name = "motif_absence", schema = Constant.SCHEMA)
public class MotifAbsence {
    
    @Id
    @SequenceGenerator(name = "motif_absence_sequence", sequenceName = "motif_absence_sequence",schema = Constant.SCHEMA, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "motif_absence_sequence")
    @Column(name = "code", updatable = false)
    private Long code;

    @Column(name = "libelle", nullable = false)
    private String libelle;
}

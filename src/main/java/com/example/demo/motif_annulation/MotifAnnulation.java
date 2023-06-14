package com.example.demo.motif_annulation;

import com.example.demo.utils.Constant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MotifAnnulation")
@Table(name = "motif_annulation", schema = Constant.SCHEMA)
public class MotifAnnulation {

    @Id
    @SequenceGenerator(name = "motif_annulation_sequence", sequenceName = "motif_annulation_sequence",schema = Constant.SCHEMA, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "motif_annulation_sequence")
    @Column(name = "identifiant", updatable = false)
    private Long identifiant;

    @Column(name = "libelle", nullable = false)
    private String libelle;
    
}

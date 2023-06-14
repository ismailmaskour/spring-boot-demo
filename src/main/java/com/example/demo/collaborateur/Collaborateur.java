package com.example.demo.collaborateur;

import java.util.Date;

import com.example.demo.photo.Photo;
import com.example.demo.utils.Constant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Collaborateur")
@Table(name = "collaborateur", schema = Constant.SCHEMA)
public class Collaborateur {

    @Id
    // @SequenceGenerator(name = "collaborateur_sequence", sequenceName =
    // "collaborateur_sequence", allocationSize = 1)
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
    // "collaborateur_sequence")
    @Column(name = "matricule", updatable = false)
    private Long matricule;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "situation_famille", nullable = false)
    private String situationFamille;

    @Column(name = "date_naissance", nullable = false)
    private Date dateNaissance;

    @Column(name = "salaire", nullable = false)
    private float salaire;

    @JoinColumn(name = "photo")
    @OneToOne(cascade = CascadeType.ALL)
    private Photo photo;

}

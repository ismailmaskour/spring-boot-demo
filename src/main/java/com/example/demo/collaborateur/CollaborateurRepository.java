package com.example.demo.collaborateur;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.DTO.CollaboratorResponse;
import com.example.demo.utils.Constant;

public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long> {
    // @Column(name = "matricule", updatable = false)
    // private Long matricule;

    // @Column(name = "nom", nullable = false)
    // private String nom;

    // @Column(name = "prenom", nullable = false)
    // private String prenom;

    // @Column(name = "situation_famille", nullable = false)
    // private String situationFamille;

    // @Column(name = "date_naissance", nullable = false)
    // private Date dateNaissance;

    // @Column(name = "salaire", nullable = false)
    // private float salaire;

    // @JoinColumn(name = "photo")
    // @OneToOne(cascade = CascadeType.ALL)
    // private Photo photo;
    @Query(value = "SELECT"
            + " c.matricule AS matricule,"
            + " c.nom AS nom,"
            + " c.prenom AS prenom,"
            + " c.date_naissance AS dateNaissance,"
            + " c.salaire AS salaire,"
            + " c.situation_famille AS situationFamille"
            + " FROM " + Constant.SCHEMA + ".collaborateur c", nativeQuery = true)
    Page<CollaboratorResponse> getCollaborateursByCriteria(@Param("matricule") Long matricule, @Param("nom") String nom,
            @Param("prenom") String prenom, Pageable pageable);
    // @Query(value = "SELECT"
    // + " c.matricule AS matricule,"
    // + " c.nom AS nom,"
    // + " c.prenom AS prenom,"
    // + " c.date_naissance AS dateNaissance,"
    // + " c.salaire AS salaire,"
    // + " c.situation_famille AS situationFamille"
    // + " FROM " + Constant.SCHEMA + ".collaborateur c")
    // Page<CollaboratorResponse> getCollaborateursByCriteria(@Param("matricule")
    // Long matricule, @Param("nom") String nom,
    // @Param("prenom") String prenom, Pageable pageable);

    Page<Collaborateur> findAll(Pageable pageable);


}

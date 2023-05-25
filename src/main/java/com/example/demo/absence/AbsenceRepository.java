package com.example.demo.absence;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.DTO.AbsenseResult;
import com.example.demo.utils.Constant;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {

    @Query(value = "SELECT ab.identifiant, ab.collaborateur, ab.date_debut dateDebut, ab.date_fin dateFin, mab.libelle motif FROM "
            + Constant.SCHEMA
            + ".absence ab, motif_absence mab WHERE ab.collaborateur = :matricule AND ab.code = mab.code", nativeQuery = true)
    public Collection<AbsenseResult> findByMatricule(@Param("matricule") Long matricule);

}
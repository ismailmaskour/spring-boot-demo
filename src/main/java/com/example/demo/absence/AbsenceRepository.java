package com.example.demo.absence;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.DTO.AbsenseResult;
import com.example.demo.utils.Constant;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {

    @Query(value = "SELECT"
            + " ab.identifiant AS identifiant,"
            + " ab.collaborateur AS collaborateur,"
            + " ab.date_debut AS dateDebut,"
            + " ab.date_fin AS dateFin,"
            + " ab.cree_le AS creeLe,"
            + " (SELECT CONCAT(userC.firstname,' ',userC.lastname) FROM _user userC WHERE id = ab.cree_par) AS creePar,"
            + " ab.valide_le valideLe,"
            + " (SELECT CONCAT(userV.firstname,' ',userV.lastname) FROM _user userV WHERE id = ab.valide_par) AS validePar,"
            + " ab.annule_le annuleLe,"
            + " (SELECT CONCAT(userA.firstname,' ',userA.lastname) FROM _user userA WHERE id= ab.annule_par) AS annulePar,"
            + " ab.statut AS statut,"
            + " mab.libelle AS motifName,"
            + " mab.code AS motifId,"
            + " (SELECT libelle FROM "+Constant.SCHEMA+".motif_annulation WHERE identifiant = ab.motif_annulation) AS motifAnnulation"
            + " FROM " + Constant.SCHEMA + ".absence ab, "+ Constant.SCHEMA +".motif_absence mab,"+ Constant.SCHEMA +"._user u"
            + " WHERE ab.collaborateur = :matricule"
            + " AND ab.motif = mab.code"
            + " AND ab.cree_par = u.id"
            + " ORDER BY ab.identifiant"
            + " LIMIT :limit OFFSET :first",
            nativeQuery = true)
    public List<AbsenseResult> findByMatricule(@Param("matricule") Long matricule, @Param("first") int first, @Param("limit") int limit);



    @Query(value = "SELECT COUNT(*)"
            + " FROM " + Constant.SCHEMA + ".absence ab, "+ Constant.SCHEMA +".motif_absence mab,"+ Constant.SCHEMA +"._user u"
            + " WHERE ab.collaborateur = :matricule"
            + " AND ab.motif = mab.code"
            + " AND ab.cree_par = u.id",
            nativeQuery = true)
    public int getRowsNumber(@Param("matricule") Long matricule);
    

    @Query(nativeQuery = true, value = "SELECT COUNT(*) From " + Constant.SCHEMA
            + ".absence WHERE collaborateur = :matricule"
            + " AND (:dateDebut BETWEEN date_debut AND date_fin"
            + " OR date_debut BETWEEN :dateDebut AND  :dateFin)")
    public int getCount(@Param("matricule") Long matricule, @Param("dateDebut") Date dateDebut,
            @Param("dateFin") Date dateDefin);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) From " + Constant.SCHEMA
            + ".absence ab WHERE collaborateur = :matricule AND (:absenceId IS NULL OR ab.identifiant <> :absenceId)"
            + " AND (:dateDebut BETWEEN date_debut AND date_fin"
            + " OR date_debut BETWEEN :dateDebut AND :dateFin)")
    public int getCount(@Param("matricule") Long matricule, @Param("absenceId") Long absenceId, @Param("dateDebut") Date dateDebut,
            @Param("dateFin") Date dateDefin);

    public Absence findByIdentifiant(Long identifiant);


}
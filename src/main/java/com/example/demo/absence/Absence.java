package com.example.demo.absence;

import java.util.Date;

import com.example.demo.motif_absence.MotifAbsence;
import com.example.demo.utils.Constant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "Absence")
@Table(name = "absence", schema = Constant.SCHEMA)
public class Absence {

    @Id
    @SequenceGenerator(name = "absence_sequence", sequenceName = "absence_sequence", schema = Constant.SCHEMA, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "absence_sequence")
    @Column(name = "identifiant", updatable = false)
    private Long identifiant;

    @Column(name = "collaborateur", nullable = false)
    private Long collaborateur;

    @Column(name = "date_debut", nullable = false)
    private Date dateDebut;

    @Column(name = "date_fin", nullable = false)
    private Date dateFin;

    @JoinColumn(name = "code", foreignKey = @ForeignKey(name = "fk_absence_motif_absence"), nullable = false)
    @ManyToOne()
    private MotifAbsence motif;

}

// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Embeddable
// class AbsenceId {

// @ManyToOne()
// @JoinColumn(name = "matricule", nullable = false)
// // @JsonBackReference
// private Collaborateur collaborateur;
// @Column(name = "date_debut", nullable = false)
// private Date dateDebut;

// }
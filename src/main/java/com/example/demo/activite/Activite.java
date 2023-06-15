package com.example.demo.activite;

import java.io.Serializable;
import java.util.Date;

import com.example.demo.utils.Constant;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Activite")
@Table(name = "activite", schema = Constant.SCHEMA)
public class Activite {

    @EmbeddedId
    private ActiviteId activiteId;

    private Date au;
    private String commentaire;

}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
class ActiviteId implements Serializable {

    private String titre;
    private Date du;

}
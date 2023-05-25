package com.example.demo.DTO;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbsenceRequest {

    private Long matricule;
    private Date dateDebut;
    private Date dateFin;
    private Long motif;

}
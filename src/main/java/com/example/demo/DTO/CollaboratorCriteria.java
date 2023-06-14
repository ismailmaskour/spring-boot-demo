package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollaboratorCriteria {
    
    private Long matricule;
    private String nom;
    private String prenom;
    private int page;
    private int size;

}

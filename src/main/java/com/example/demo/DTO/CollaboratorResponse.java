package com.example.demo.DTO;

import java.util.Date;

public interface CollaboratorResponse {
    
    public Long getMatricule();
    public String getNom();
    public String getPrenom();
    public Date getDateNaissance();
    public float getSalaire();
    public String getSituationFamille();
    // public String getPhoto();
}

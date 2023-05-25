package com.example.demo.DTO;

import java.util.Date;

public interface AbsenseResult {
    Long getIdentifiant();
	Date getDateDebut();
	Date getDateFin();
	Long getCollaborateur();
	String getMotif();
}

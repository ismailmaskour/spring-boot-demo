package com.example.demo.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
public interface AbsenseResult {

    Long getIdentifiant();
	Date getDateDebut();
	Date getDateFin();
	Long getCollaborateur();
	Long getMotifId();
	String getMotifName();
	Date getCreeLe();
	String getCreePar();
	Date getValideLe();
	String getValidePar();
	Date getAnnuleLe();
	String getAnnulePar();
	String getMotifAnnulation();
	String getStatut();
}

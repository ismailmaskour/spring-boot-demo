package com.example.demo.absence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.AbsenceRequest;
import com.example.demo.DTO.ResponseDTO;
import com.example.demo.collaborateur.Collaborateur;
import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.motif_absence.MotifAbsence;
import com.example.demo.utils.Constant;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AbsenceResource {

    private final AbsenceService absenceService;

    private static final Logger logger = LogManager.getLogger(AbsenceResource.class);

    @PostMapping("/createAbsence")
    public ResponseEntity<ResponseDTO> createAbsence(@RequestBody AbsenceRequest absenceReq)
            throws ItemNotFoundException {
        try {
            System.out.println(absenceReq.toString());
            Absence absenceIn = new Absence();

            absenceIn.setCollaborateur(absenceReq.getMatricule());
            absenceIn.setDateDebut(absenceReq.getDateDebut());
            absenceIn.setDateFin(absenceReq.getDateFin());
            MotifAbsence mAbsence = new MotifAbsence();
            mAbsence.setCode(absenceReq.getMotif());
            absenceIn.setMotif(mAbsence);

            ResponseDTO response = this.absenceService.createAbsence(absenceIn);

            if (Constant.CODE_MESSAGE_EREUR.equals(response.getCodeMessage())) {
                return new ResponseEntity<ResponseDTO>(response, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erreur Technique {}", e);
            return new ResponseEntity<ResponseDTO>(
                    new ResponseDTO(Constant.CODE_MESSAGE_EREUR, "Erreur Technique", null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getAbsencesByCollaborator")
    public ResponseEntity<ResponseDTO> getAbsencesByCollaborator(@RequestBody Collaborateur collaborateur)
            throws ItemNotFoundException {
        try {

            ResponseDTO response = this.absenceService.getAbsencesByCollaborator(collaborateur);

            if (Constant.CODE_MESSAGE_EREUR.equals(response.getCodeMessage())) {
                return new ResponseEntity<ResponseDTO>(response, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erreur Technique {}", e);
            return new ResponseEntity<ResponseDTO>(
                    new ResponseDTO(Constant.CODE_MESSAGE_EREUR, "Erreur Technique", null), HttpStatus.BAD_REQUEST);
        }
    }
}

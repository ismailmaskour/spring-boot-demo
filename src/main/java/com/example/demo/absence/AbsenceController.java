package com.example.demo.absence;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.AbsenceRequest;
import com.example.demo.DTO.AbsenseResult;
import com.example.demo.DTO.FindAbsenceByCollaboratorRequest;
import com.example.demo.DTO.ResponseDTO;
import com.example.demo.collaborateur.Collaborateur;
import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.motif_absence.MotifAbsence;
import com.example.demo.utils.Constant;
import com.example.demo.utils.SecurityConstant;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AbsenceController {

    private final AbsenceService absenceService;

    private static final Logger logger = LogManager.getLogger(AbsenceController.class);

    @PostMapping("/createAbsence")
    public ResponseEntity<ResponseDTO> createAbsence(@RequestBody AbsenceRequest absenceRequest, @RequestHeader(name = SecurityConstant.HEADER_STRING) String jwtToken)
            throws ItemNotFoundException {
        try {

            ResponseDTO response = this.absenceService.createAbsence(absenceRequest, jwtToken);

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
    @PostMapping("/updateAbsence")
    public ResponseEntity<ResponseDTO> updateAbsence(@RequestBody AbsenceRequest absence, @RequestHeader(name = SecurityConstant.HEADER_STRING) String jwtToken)
            throws ItemNotFoundException {
        try {

            ResponseDTO response = this.absenceService.updateAbsence(absence, jwtToken);

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
    public ResponseEntity<ResponseDTO> getAbsencesByCollaborator(@RequestBody FindAbsenceByCollaboratorRequest request)
            throws ItemNotFoundException {
        try {

            ResponseDTO response = this.absenceService.getAbsencesByCollaborator(request);

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
    @PostMapping("/validateAbsance")
    public ResponseEntity<ResponseDTO> validateAbsance(@RequestBody List<Long> ids, @RequestHeader(name = SecurityConstant.HEADER_STRING) String jwtToken)
            throws ItemNotFoundException {
        try {

            ResponseDTO response = this.absenceService.validateAbsence(ids, jwtToken);

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
    @PostMapping("/cancelAbsance")
    public ResponseEntity<ResponseDTO> cancelAbsance(@RequestBody List<Long> ids, @RequestHeader(name = SecurityConstant.HEADER_STRING) String jwtToken)
            throws ItemNotFoundException {
        try {

            ResponseDTO response = this.absenceService.cancelAbsance(ids, jwtToken);

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

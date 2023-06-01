package com.example.demo.collaborateur;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.ResponseDTO;
import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.security.config.JwtService;
import com.example.demo.utils.Constant;
import com.example.demo.utils.SecurityConstant;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CollaborateurController {

    private final CollaborateurService collaborateurService;
    private final JwtService jwtService;

    private static final Logger logger = LogManager.getLogger(CollaborateurController.class);

    @PostMapping("/createCollaborator")
    public ResponseEntity<ResponseDTO> createCollaborator(@RequestBody Collaborateur collaborateur)
            throws ItemNotFoundException {
        try {

            ResponseDTO response = this.collaborateurService.createCollaborator(collaborateur);

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

    @PostMapping("/updateCollaborator")
    public ResponseEntity<ResponseDTO> updateCollaborator(@RequestBody Collaborateur collaborateur)
            throws ItemNotFoundException {
        try {

            ResponseDTO response = this.collaborateurService.updateCollaborator(collaborateur);

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
    
    @PostMapping("/deleteCollaborator")
    public ResponseEntity<ResponseDTO> deleteCollaborator(@RequestBody Collaborateur collaborateur)
            throws ItemNotFoundException {
        try {

            ResponseDTO response = this.collaborateurService.deleteCollaborator(collaborateur);

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

    @PostMapping("/getCollaborators")
    public ResponseEntity<ResponseDTO> getCollaborators(@RequestHeader(name = SecurityConstant.HEADER_STRING) String jwtToken)
            throws ItemNotFoundException {
        try {

            ResponseDTO response = this.collaborateurService.getCollaborators(jwtToken);

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

package com.example.demo.motif_annulation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.ResponseDTO;
import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.utils.Constant;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MotifAnnulationController {
    
    private static final Logger logger = LogManager.getLogger(MotifAnnulationController.class);

    private final MotifAnnulationService motifAnnulationService;


    @GetMapping("/getAllMotifAnnulation")
    public ResponseEntity<ResponseDTO> getAllMotifAbsence()
            throws ItemNotFoundException {
        try {

            ResponseDTO response = this.motifAnnulationService.getAllMotifAnnulation();

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

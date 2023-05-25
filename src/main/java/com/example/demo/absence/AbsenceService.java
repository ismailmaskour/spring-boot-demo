package com.example.demo.absence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ResponseDTO;
import com.example.demo.collaborateur.Collaborateur;
import com.example.demo.utils.Constant;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class AbsenceService {

    private final AbsenceRepository absenceRepository;

    private static final Logger logger = LogManager.getLogger(AbsenceService.class);

    public ResponseDTO createAbsence(Absence absence) {
        ResponseDTO response = new ResponseDTO();

        return new ResponseDTO(Constant.CODE_MESSAGE_OK, "L'absence a été ajouté avec succès",
                absenceRepository.save(absence));
    }

    public ResponseDTO getAbsencesByCollaborator(Collaborateur collaborateur) {
        ResponseDTO response = new ResponseDTO();

        return new ResponseDTO(Constant.CODE_MESSAGE_OK, "",
                absenceRepository.findByMatricule(collaborateur.getMatricule()));

    }

}

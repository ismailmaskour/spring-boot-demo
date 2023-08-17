package com.example.demo.absence;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.AbsenceRequest;
import com.example.demo.DTO.AnnulerAbsence;
import com.example.demo.DTO.FindAbsenceByCollaboratorRequest;
import com.example.demo.DTO.ResponseDTO;
import com.example.demo.DTO.ResultPagination;
import com.example.demo.DTO.Statut;
import com.example.demo.security.config.JwtService;
import com.example.demo.user.UserRepository;
import com.example.demo.utils.Constant;

import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class AbsenceService {

    private final AbsenceRepository absenceRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    private static final Logger logger = LogManager.getLogger(AbsenceService.class);

    public ResponseDTO createAbsence(AbsenceRequest absenceRequest, String jwtToken) {
        ResponseDTO response = new ResponseDTO();

        if (absenceRequest.getDateDebut() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Date de début obligatoire");
            return response;
        }
        if (absenceRequest.getDateFin() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Date fin obligatoire");
            return response;
        }
        if (absenceRequest.getDateDebut().after(absenceRequest.getDateFin())) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Date de début doit être antérieure à date fin");
            return response;
        }
        if (absenceRequest.getMotif() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Motif est obligatoire");
            return response;
        }

        int count = absenceRepository.getCount(absenceRequest.getMatricule(), absenceRequest.getIdentifiant(),
                absenceRequest.getDateDebut(),
                absenceRequest.getDateFin());
        if (count > 0) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Impossible d'ajouter l'absence, Veuillez vérifier la période choisie !");
            return response;
        }

        String subject = jwtService.extractClaim(jwtToken.substring(7), Claims::getSubject);

        Absence absence = new Absence();
        absence.setCollaborateur(absenceRequest.getMatricule());
        absence.setDateDebut(absenceRequest.getDateDebut());
        absence.setDateFin(absenceRequest.getDateFin());
        absence.setMotif(absenceRequest.getMotif());
        absence.setCreeLe(new Date());
        absence.setCreePar(userRepository.getUserInfo(subject).getId());
        absence.setStatut(Statut.AT);

        return new ResponseDTO(Constant.CODE_MESSAGE_OK, "L'absence a été ajouté avec succès",
                absenceRepository.save(absence));
    }

    public ResponseDTO updateAbsence(AbsenceRequest absenceRequest, String jwtToken) {
        ResponseDTO response = new ResponseDTO();

        Optional<Absence> optAbsence = absenceRepository.findById(absenceRequest.getIdentifiant());
        if (!optAbsence.isPresent()) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("L'absence est introuvable");
            return response;
        }

        Absence absenceOld = optAbsence.get();
        if (!absenceOld.getStatut().equals(Statut.AT)) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Impossible de modifier l'absence, L'absence doit être 'En attente' pour la modifier");
            return response;
        }

        if (absenceRequest.getDateDebut() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Date de début obligatoire");
            return response;
        }
        if (absenceRequest.getDateFin() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Date fin obligatoire");
            return response;
        }

        if (absenceRequest.getDateDebut().after(absenceRequest.getDateFin())) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Date de début doit être antérieure à date fin");
            return response;
        }

        if (absenceRequest.getMotif() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Motif est obligatoire");
            return response;
        }

        int count = absenceRepository.getCount(absenceRequest.getMatricule(), absenceRequest.getIdentifiant(),
                absenceRequest.getDateDebut(),
                absenceRequest.getDateFin());
        if (count > 0) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Impossible de modifier l'absence, Veuillez vérifier la période choisie!");
            return response;
        }

        absenceOld.setDateDebut(absenceRequest.getDateDebut());
        absenceOld.setDateFin(absenceRequest.getDateFin());
        absenceOld.setMotif(absenceRequest.getMotif());

        return new ResponseDTO(Constant.CODE_MESSAGE_OK, "L'absence a été modifiée avec succès",
                absenceRepository.save(absenceOld));
    }

    public ResponseDTO getAbsencesByCollaborator(FindAbsenceByCollaboratorRequest request) {
        ResponseDTO response = new ResponseDTO();

        if (request.getFirst() < 0) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Le champ First doit être supérieure ou égale 0");
            return response;
        }

        return new ResponseDTO(Constant.CODE_MESSAGE_OK, "",
                new ResultPagination(absenceRepository.getRowsNumber(request.getMatricule()), absenceRepository
                        .findByMatricule(request.getMatricule(), request.getFirst(), request.getLimit())));
    }

    public ResponseDTO validateAbsence(List<Long> ids, String jwtToken) {
        ResponseDTO response = new ResponseDTO();

        if (ids.size() == 0) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Aucun absence sélectionné!");
            return response;
        }
        for (int i = 0; i < ids.size(); i++) {
            Absence absenceOld = absenceRepository.findByIdentifiant(ids.get(i));
            if (absenceOld != null) {
                if (absenceOld.getStatut() == Statut.AT) {
                    String subject = jwtService.extractClaim(jwtToken.substring(7), Claims::getSubject);
                    absenceOld.setStatut(Statut.VA);
                    absenceOld.setValideLe(new Date());
                    absenceOld.setValidePar(userRepository.getUserInfo(subject).getId());
                    absenceRepository.save(absenceOld);
                }
            }
        }
        // if (absenceOld == null) {
        // response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
        // response.setMessage("L'absence est introuvable!");
        // return response;
        // }
        // if (absenceOld.getStatut() != Statut.AT) {
        // response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
        // response.setMessage("Impossible de valider l'absence!");
        // return response;
        // }
        String message;
        if (ids.size() == 1) {
            message = "Absence sélectionnée est validée avec succès";
        } else {
            message = "Absences sélectionnées sont validées avec succès";
        }

        return new ResponseDTO(Constant.CODE_MESSAGE_OK, message,
                null);
    }

    public ResponseDTO cancelAbsance(AnnulerAbsence annulerAbsence, String jwtToken) {
        ResponseDTO response = new ResponseDTO();
        Absence absenceOld = absenceRepository.findByIdentifiant(annulerAbsence.getIdentifiant());
        if (absenceOld == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("L'absence est introuvable!");
            return response;
        }
        if (annulerAbsence.getMotif() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Motif obligatoire!");
            return response;
        }
        if (!absenceOld.getStatut().equals(Statut.VA) && !absenceOld.getStatut().equals(Statut.AT)) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Impossible d'annuler l'absence!");
            return response;
        }
        String subject = jwtService.extractClaim(jwtToken.substring(7), Claims::getSubject);
        absenceOld.setStatut(Statut.AN);
        absenceOld.setAnnuleLe(new Date());
        absenceOld.setAnnulePar(userRepository.getUserInfo(subject).getId());
        absenceOld.setMotifAnnulation(annulerAbsence.getMotif());
        return new ResponseDTO(Constant.CODE_MESSAGE_OK, "L'absence a été annulée avec succès",
                absenceRepository.save(absenceOld));
    }

}

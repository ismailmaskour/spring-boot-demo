package com.example.demo.collaborateur;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ResponseDTO;
import com.example.demo.DTO.UserInfo;
import com.example.demo.security.config.JwtService;
import com.example.demo.utils.Constant;

import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class CollaborateurService {

    private final CollaborateurRepository collaborateurRepository;
    private final JwtService jwtService;

    private static final Logger logger = LogManager.getLogger(CollaborateurService.class);

    public ResponseDTO createCollaborator(Collaborateur collaborateur) {
        ResponseDTO response = new ResponseDTO();

        if (collaborateur.getMatricule() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Le matricule est obligatoire!");
            return response;
        }

        Optional<Collaborateur> c = collaborateurRepository.findById(collaborateur.getMatricule());
        if (c.isPresent()) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Le matricule déjà existe!");
            return response;
        }

        if (collaborateur.getNom() == null || collaborateur.getNom().trim().equalsIgnoreCase("")) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Le Nom est obligatoire!");
            return response;
        }

        if (collaborateur.getNom().length() < 3) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Le nom doit comporter 3 caractères ou plus");
            return response;
        }

        if (collaborateur.getPrenom() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Le Prénom est obligatoire!");
            return response;
        }

        if (collaborateur.getSituationFamille() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("La situation de famille est obligatoire!");
            return response;
        }

        if (collaborateur.getDateNaissance() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("La date de naissance est obligatoire!");
            return response;
        }

        if (collaborateur.getDateNaissance().after(new Date())) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("La date de naissance doit être inférieure à la date du jour!");
            return response;
        }

        if (collaborateur.getSalaire() <= 0) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Le salaire doit être supérieur à 0");
            return response;
        }

        return new ResponseDTO(Constant.CODE_MESSAGE_OK, "Le collaborateur a été ajouté avec succès",
                collaborateurRepository.save(collaborateur));

    }

    public ResponseDTO updateCollaborator(Collaborateur collaborateur) {
        ResponseDTO response = new ResponseDTO();

        Optional<Collaborateur> optCollaborator = collaborateurRepository.findById(collaborateur.getMatricule());

        if (!optCollaborator.isPresent()) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Le collaborateur est introuvable");
            return response;
        }

        if (collaborateur.getNom() == null || collaborateur.getNom().trim().equalsIgnoreCase("")) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Le Nom est obligatoire!");
            return response;
        }

        if (collaborateur.getNom().length() < 3) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Le nom doit comporter 3 caractères ou plus");
            return response;
        }

        if (collaborateur.getPrenom() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Le Prénom est obligatoire!");
            return response;
        }

        if (collaborateur.getSituationFamille() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("La situation de famille est obligatoire!");
            return response;
        }

        if (collaborateur.getDateNaissance() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("La date de naissance est obligatoire!");
            return response;
        }

        if (collaborateur.getDateNaissance().after(new Date())) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("La date de naissance doit être inférieure à la date du jour!");
            return response;
        }

        if (collaborateur.getSalaire() <= 0) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Le salaire doit être positive!");
            return response;
        }

        return new ResponseDTO(Constant.CODE_MESSAGE_OK, "Le collaborateur a été modifié avec succès",
                collaborateurRepository.save(collaborateur));
    }

    public ResponseDTO deleteCollaborator(Collaborateur collaborateur) {
        ResponseDTO response = new ResponseDTO();

        Optional<Collaborateur> optCollaborator = collaborateurRepository.findById(collaborateur.getMatricule());

        if (!optCollaborator.isPresent()) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Le collaborateur est introuvable");
            return response;
        }

        collaborateurRepository.delete(collaborateur);
        return new ResponseDTO(Constant.CODE_MESSAGE_OK, "Le collaborateur a été supprimé avec succès", null);
    }

    public ResponseDTO getCollaborators(String jwtToken) {

        // String token = jwtToken.substring(7); // Remove "Bearer " prefix
        // Claims claims = jwtService.extractAllClaims(token);
        //  Map map = claims.get("userInfos", HashMap.class);

        // System.out.println(map);

        UserInfo userInfo = jwtService.extractUserInfos(jwtToken.substring(7));
        System.out.println(userInfo.toString());

        var subject = jwtService.extractClaim(jwtToken.substring(7), Claims::getSubject);
        System.out.println(subject);

        return new ResponseDTO(Constant.CODE_MESSAGE_OK, null, collaborateurRepository.findAll());
    }

}

package com.example.demo.collaborateur;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CollaboratorCriteria;
import com.example.demo.DTO.ResponseDTO;
import com.example.demo.DTO.ResultPagination;
import com.example.demo.security.config.JwtService;
import com.example.demo.user.Role;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
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
    private final UserRepository userRepository;

    private static final Logger logger = LogManager.getLogger(CollaborateurService.class);

    public ResponseDTO createCollaborator(Collaborateur collaborateur, String jwtToken) {
        ResponseDTO response = new ResponseDTO();

        String subject = jwtService.extractClaim(jwtToken.substring(7), Claims::getSubject);

        User user = userRepository.getUserInfo(subject);
        if (!user.getRole().equals(Role.ADMIN)) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Vous n'avez pas le droit d'ajouter un collaborateur !");
            return response;
        }

        if (collaborateur.getMatricule() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Matricule est obligatoire!");
            return response;
        }

        Optional<Collaborateur> c = collaborateurRepository.findById(collaborateur.getMatricule());
        if (c.isPresent()) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Matricule déjà existe!");
            return response;
        }

        if (collaborateur.getNom() == null || collaborateur.getNom().trim().equalsIgnoreCase("")) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Nom est obligatoire!");
            return response;
        }

        if (collaborateur.getPrenom() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Le Prénom est obligatoire!");
            return response;
        }

        if (collaborateur.getPrenom().length() < 3) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Prénom doit comporter 3 caractères ou plus");
            return response;
        }

        if (collaborateur.getSituationFamille() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Situation de famille est obligatoire!");
            return response;
        }

        if (collaborateur.getDateNaissance() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Date de naissance est obligatoire!");
            return response;
        }

        if (collaborateur.getDateNaissance().after(new Date())) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Date de naissance doit être inférieure à la date du jour!");
            return response;
        }

        if (collaborateur.getSalaire() < 1000) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Salaire doit être supérieur ou égal 1000.00");
            return response;
        }

        return new ResponseDTO(Constant.CODE_MESSAGE_OK, "Le collaborateur a été ajouté avec succès",
                collaborateurRepository.save(collaborateur));

    }

    public ResponseDTO updateCollaborator(Collaborateur collaborateur, String jwtToken) {
        ResponseDTO response = new ResponseDTO();

        String subject = jwtService.extractClaim(jwtToken.substring(7), Claims::getSubject);

        User user = userRepository.getUserInfo(subject);
        if (!user.getRole().equals(Role.ADMIN)) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Vous n'avez pas le droit de modifier un collaborateur !");
            return response;
        }

        Optional<Collaborateur> optCollaborator = collaborateurRepository.findById(collaborateur.getMatricule());

        if (!optCollaborator.isPresent()) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Le collaborateur est introuvable");
            return response;
        }

        if (collaborateur.getNom() == null || collaborateur.getNom().trim().equalsIgnoreCase("")) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Nom est obligatoire!");
            return response;
        }

        if (collaborateur.getPrenom() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Le Prénom est obligatoire!");
            return response;
        }

        if (collaborateur.getPrenom().length() < 3) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Prénom doit comporter 3 caractères ou plus");
            return response;
        }

        if (collaborateur.getSituationFamille() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Situation de famille est obligatoire!");
            return response;
        }

        if (collaborateur.getDateNaissance() == null) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Date de naissance est obligatoire!");
            return response;
        }

        if (collaborateur.getDateNaissance().after(new Date())) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Date de naissance doit être inférieure à la date du jour!");
            return response;
        }

        if (collaborateur.getSalaire() < 1000) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Salaire doit être supérieur ou égal 1000.00");
            return response;
        }

        return new ResponseDTO(Constant.CODE_MESSAGE_OK, "Le collaborateur a été modifié avec succès",
                collaborateurRepository.save(collaborateur));
    }

    public ResponseDTO deleteCollaborator(Collaborateur collaborateur, String jwtToken) {
        ResponseDTO response = new ResponseDTO();

        String subject = jwtService.extractClaim(jwtToken.substring(7), Claims::getSubject);

        User user = userRepository.getUserInfo(subject);
        if (!user.getRole().equals(Role.ADMIN)) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Vous n'avez pas le droit de supprimer un collaborateur !");
            return response;
        }

        Optional<Collaborateur> optCollaborator = collaborateurRepository.findById(collaborateur.getMatricule());

        if (!optCollaborator.isPresent()) {
            response.setCodeMessage(Constant.CODE_MESSAGE_EREUR);
            response.setMessage("Le collaborateur est introuvable");
            return response;
        }

        collaborateurRepository.delete(collaborateur);
        return new ResponseDTO(Constant.CODE_MESSAGE_OK, "Le collaborateur a été supprimé avec succès", null);
    }

    public ResponseDTO getCollaborators(CollaboratorCriteria cc, String jwtToken) {

        // String token = jwtToken.substring(7); // Remove "Bearer " prefix
        // Claims claims = jwtService.extractAllClaims(token);
        // Map map = claims.get("userInfos", HashMap.class);

        // System.out.println(map);

        // UserInfo userInfo = jwtService.extractUserInfos(jwtToken.substring(7));
        // System.out.println(userInfo.toString());

        // var subject = jwtService.extractClaim(jwtToken.substring(7),
        // Claims::getSubject);
        // System.out.println(subject);
        // PageRequest pageRequest = PageRequest.of(cc.getPage(), cc.getSize());
        return new ResponseDTO(Constant.CODE_MESSAGE_OK, null, collaborateurRepository.findAll());
    }

    public ResponseDTO getCollaborateursByCriteria(CollaboratorCriteria cc, String jwtToken) {

        PageRequest pageRequest = PageRequest.of(cc.getPage(), cc.getSize());

        Page<Collaborateur> collaborateurs = collaborateurRepository.findAll(pageRequest);

        ResultPagination rp = new ResultPagination();
        rp.setResult(collaborateurs.getContent());
        rp.setRowsNumber(collaborateurs.getTotalElements());

        return new ResponseDTO(Constant.CODE_MESSAGE_OK, null, rp);
    }

}

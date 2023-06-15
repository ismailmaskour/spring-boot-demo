package com.example.demo.activite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ResponseDTO;
import com.example.demo.security.config.JwtService;
import com.example.demo.user.UserRepository;
import com.example.demo.utils.Constant;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class ActiviteService {

    private final ActiviteRepository activiteRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    private static final Logger logger = LogManager.getLogger(ActiviteService.class);

    public ResponseDTO createActivity(Activite activite, String jwtToken) {
        ResponseDTO response = new ResponseDTO();

        return new ResponseDTO(Constant.CODE_MESSAGE_OK, "L'activité a été ajouté avec succès",
                activiteRepository.save(activite));
    }
}

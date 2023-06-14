package com.example.demo.motif_annulation;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.ResponseDTO;
import com.example.demo.utils.Constant;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MotifAnnulationService {
    private final MotifAnnulationRepository motifAnnulationRepository;
    
    public ResponseDTO getAllMotifAnnulation() {
        return new ResponseDTO(Constant.CODE_MESSAGE_OK, null, motifAnnulationRepository.findAll());
    }
}

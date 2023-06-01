package com.example.demo.motif_absence;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.ResponseDTO;
import com.example.demo.utils.Constant;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MotifAbsenceService {
    
    private final MotifAbseceRepository motifAbseceRepository;
    
    public ResponseDTO getAllMotifAbsence() {
        return new ResponseDTO(Constant.CODE_MESSAGE_OK, null, motifAbseceRepository.findAll());
    }

}

package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindAbsenceByCollaboratorRequest {

    private Long matricule;
    private int first;
    private int limit;

}

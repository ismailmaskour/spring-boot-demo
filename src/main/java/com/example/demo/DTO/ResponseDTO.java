package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private String codeMessage;
    private String message;
    private Object data;

    public ResponseDTO(String message, Object data) {
        super();
        this.message = message;
        this.data = data;
    }

}

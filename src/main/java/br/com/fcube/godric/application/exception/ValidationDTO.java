package br.com.fcube.godric.application.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationDTO {

    private String field;
    private String error;
}

package com.API.listaTareas.exception.modeloError;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorJson {

    private int code;
    private String message;

}

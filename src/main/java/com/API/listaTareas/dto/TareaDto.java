package com.API.listaTareas.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TareaDto {

    private Long idTarea;
    private String nombreTarea;
    private String descripcionTarea;
    private String estado;
    private String personaAsignada;
    private String fechaInicio;
    private String fechaFin;

}

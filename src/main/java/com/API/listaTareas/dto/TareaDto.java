package com.API.listaTareas.dto;

import com.API.listaTareas.model.Usuario;
import jakarta.persistence.*;
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

    private Usuario usuario;

}

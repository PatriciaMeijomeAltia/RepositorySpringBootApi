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

    private Integer id;
    private String descripcion;
    private Boolean completada;

    private Usuario usuario;

}

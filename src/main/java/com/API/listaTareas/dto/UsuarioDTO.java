package com.API.listaTareas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    @JsonProperty("nombreUser")
    private String nombre;
    @JsonProperty("telefonoUser")
    private String telefono;

    private List<TareaDto> tareas;
}

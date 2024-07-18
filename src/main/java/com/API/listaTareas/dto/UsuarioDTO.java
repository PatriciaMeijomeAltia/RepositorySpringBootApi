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

    private Long idUser;
    @JsonProperty("nombreUser")
    private String nombreUser;
    @JsonProperty("telefonoUser")
    private String telefonoUser;

    private List<TareaDto> tareas;
}

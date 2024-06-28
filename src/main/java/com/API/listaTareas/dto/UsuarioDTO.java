package com.API.listaTareas.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long idUSer;
    private String nombreUser;
    private String telefonoUser;

    private List<TareaDto> tareas;
}

package com.API.listaTareas.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long idUSer;
    private String nombreUser;
    private String telefonoUser;
}

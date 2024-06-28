package com.API.listaTareas.service;

import com.API.listaTareas.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {

    UsuarioDTO crearUser(UsuarioDTO usuarioDTO);

    UsuarioDTO eliminarUser(Long id);

    UsuarioDTO modificarUser(Long Id,UsuarioDTO usuarioDTO);

    List<UsuarioDTO>mostrarUser();
}

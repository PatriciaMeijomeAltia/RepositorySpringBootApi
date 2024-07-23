package com.API.listaTareas.service;

//import com.API.listaTareas.dto.UsuarioDTO;
import com.baeldung.openapi.model.UsuarioDTO;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;

import java.util.List;


public interface UsuarioService {

    UsuarioDTO crearUser(UsuarioDTO usuarioDTO);

    UsuarioDTO eliminarUser(Integer id);

    UsuarioDTO modificarUser(Integer Id,UsuarioDTO usuarioDTO);

    List<UsuarioDTO>mostrarUser();
}

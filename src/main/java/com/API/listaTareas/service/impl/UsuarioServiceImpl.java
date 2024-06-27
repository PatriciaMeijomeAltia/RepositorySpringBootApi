package com.API.listaTareas.service.impl;

import com.API.listaTareas.dto.UsuarioDTO;
import com.API.listaTareas.repository.UsuarioRepository;
import com.API.listaTareas.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO crearUser(UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public UsuarioDTO eliminarUser(Long id) {
        return null;
    }

    @Override
    public UsuarioDTO modificarUser(Long Id, UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public List<UsuarioDTO> mostrarUser() {
        return null;
    }
}

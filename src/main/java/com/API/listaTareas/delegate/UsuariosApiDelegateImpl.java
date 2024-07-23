package com.API.listaTareas.delegate;

import com.API.listaTareas.mapper.UsuarioMapper;
import com.API.listaTareas.model.Usuario;
import com.API.listaTareas.repository.UsuarioRepository;
import com.API.listaTareas.service.UsuarioService;
import com.baeldung.openapi.api.UsuariosApiDelegate;
import com.baeldung.openapi.model.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
public class UsuariosApiDelegateImpl implements UsuariosApiDelegate {

    private UsuarioService usuarioService;

    public UsuariosApiDelegateImpl(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public ResponseEntity<UsuarioDTO> crearUsuario(UsuarioDTO usuarioDTO) {

        return ResponseEntity.ok(usuarioService.crearUser(usuarioDTO));
    }

    @Override
    public ResponseEntity<UsuarioDTO> eliminarUsuario(Integer id) {

        return ResponseEntity.ok(usuarioService.eliminarUser(id));
    }

    @Override
    public ResponseEntity<UsuarioDTO> modificarUsuario(Integer id, UsuarioDTO usuarioDTO) {

        return ResponseEntity.ok(usuarioService.modificarUser(id,usuarioDTO));
    }

    @Override
    public ResponseEntity<List<UsuarioDTO>> verUsuario() {
        return ResponseEntity.ok(usuarioService.mostrarUser());
    }


}

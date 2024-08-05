package com.API.listaTareas.controller;


//import com.API.listaTareas.dto.UsuarioDTO;
import com.baeldung.openapi.api.UsuariosApi;
import com.baeldung.openapi.api.UsuariosApiDelegate;
import com.baeldung.openapi.model.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements UsuariosApi{

    private  UsuariosApiDelegate usuariosApiDelegate;

    public UsuarioController(UsuariosApiDelegate usuariosApiDelegate) {
        this.usuariosApiDelegate = usuariosApiDelegate;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuariosApiDelegate.crearUsuario(usuarioDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDTO>eliminarUsuario(@PathVariable Integer id){

       return usuariosApiDelegate.eliminarUsuario(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO>modificarUsuario(@PathVariable Integer id,@RequestBody UsuarioDTO usuarioDTO){

        return usuariosApiDelegate.modificarUsuario(id,usuarioDTO);

    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>>verUsuario() {

        return usuariosApiDelegate.verUsuario();
    }

}

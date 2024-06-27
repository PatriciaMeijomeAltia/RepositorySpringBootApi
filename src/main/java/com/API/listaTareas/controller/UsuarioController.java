package com.API.listaTareas.controller;

import com.API.listaTareas.dto.UsuarioDTO;
import com.API.listaTareas.repository.UsuarioRepository;
import com.API.listaTareas.service.TareasService;
import com.API.listaTareas.service.UsuarioService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioDTO>crearusuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.crearUser(usuarioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDTO>eliminarusuario(@PathVariable Long id){

        return ResponseEntity.ok(usuarioService.eliminarUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO>modificarusuario(@RequestBody UsuarioDTO usuarioDTO,@PathVariable Long id){

        return ResponseEntity.ok(usuarioService.modificarUser(id,usuarioDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<UsuarioDTO>>verusuario(){

        return ResponseEntity.ok(usuarioService.mostrarUser());
    }

}

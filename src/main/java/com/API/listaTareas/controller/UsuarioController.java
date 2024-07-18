package com.API.listaTareas.controller;


import com.API.listaTareas.dto.UsuarioDTO;
import com.API.listaTareas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioDTO>crearusuario(@RequestBody UsuarioDTO usuarioDTO){
        System.out.println("JSON recibido: " + usuarioDTO);
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

    @GetMapping()
    public ResponseEntity<List<UsuarioDTO>>verusuario(){

        return ResponseEntity.ok(usuarioService.mostrarUser());
    }

}

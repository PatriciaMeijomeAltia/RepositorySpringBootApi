package com.API.listaTareas.controller;


import com.API.listaTareas.dto.TareaDto;
import com.API.listaTareas.service.TareasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/lista_tareas")
public class TareaController {

    @Autowired
    private TareasService tareasService;


    @GetMapping
    public ResponseEntity<List<TareaDto>> verTareas() {

        return ResponseEntity.ok(tareasService.obtenerTareas());
    }

    @PostMapping
    public ResponseEntity<TareaDto> crearTarea(@RequestBody TareaDto tareaDto ) {

        return ResponseEntity.ok(tareasService.crearTarea(tareaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TareaDto> eliminarTarea(@PathVariable long id ) {

        return ResponseEntity.ok(tareasService.eliminarTarea(id));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> modificarTarea(@PathVariable long id,@RequestBody TareaDto tareaDto ) {

        try {
            return ResponseEntity.ok(tareasService.modificarTarea(id, tareaDto));
        }catch (NoSuchElementException e) {

            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaDto> verTareaID(@PathVariable long id ) {


        try {
            TareaDto tarea = tareasService.obtenerTareaPorId(id);

            return ResponseEntity.ok(tarea);

        } catch (NoSuchElementException e) {

            return ResponseEntity.notFound().build();
        }
    }

}

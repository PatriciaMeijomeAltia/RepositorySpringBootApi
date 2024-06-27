package com.API.listaTareas.controller;


import com.API.listaTareas.model.Tarea;
import com.API.listaTareas.service.TareasService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lista_Tareas")
@RequiredArgsConstructor
public class TareaController {

    @Autowired
    private TareasService tareasService;


    @GetMapping
    public ResponseEntity<List<Tarea>> verTareas() {

        List<Tarea> listaTareas = tareasService.obtenerTareas();
        return ResponseEntity.ok(listaTareas);
    }

    @PostMapping
    public ResponseEntity<String> crearTarea(@RequestBody Tarea tareas ) {

        return ResponseEntity.ok(tareasService.crearTarea(tareas));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTarea(@PathVariable long id ) {

        return ResponseEntity.ok(tareasService.eliminarTarea(id));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> modificarTarea(@PathVariable long id,@RequestBody Tarea tareas ) {

        return ResponseEntity.ok(tareasService.modificarTarea(id,tareas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verTareaID(@PathVariable long id ) {

        return ResponseEntity.ok(tareasService.obtenerTareaPorId(id));
    }

}

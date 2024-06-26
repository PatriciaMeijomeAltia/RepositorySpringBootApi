package com.API.listaTareas.controller;


import com.API.listaTareas.exception.MensajeError;
import com.API.listaTareas.model.Tarea;
import com.API.listaTareas.service.TareasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/lista_tareas")
public class TareasController {

    @Autowired
    private TareasService tareasService;


    @GetMapping
    public ResponseEntity<List<Tarea>> verTareas() {

        List<Tarea> listaTareas = tareasService.obtenerTareas();
        return ResponseEntity.ok(listaTareas);
    }

    @PostMapping
    public ResponseEntity<String> crearTarea(@RequestBody Tarea tarea ) {

        return ResponseEntity.ok(tareasService.crearTarea(tarea));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarTarea(@PathVariable long id ) {

        return ResponseEntity.ok(tareasService.eliminarTarea(id));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<String> modificarTarea(@PathVariable long id,@RequestBody Tarea tarea ) {

        return ResponseEntity.ok(tareasService.modificarTarea(id,tarea));
    }

    @GetMapping("/{id}")
    public HttpEntity<? extends Object> verTareaID(@PathVariable long id ) {


        try {
            Tarea tarea = tareasService.obtenerTareaPorId(id);

                return new ResponseEntity<>(tarea, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            MensajeError errorResponse = new MensajeError(e.getMessage());

            return new ResponseEntity<MensajeError>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

}

package com.API.listaTareas.controller;

import com.baeldung.openapi.api.ListaTareasApi;
import com.baeldung.openapi.api.ListaTareasApiDelegate;
import com.baeldung.openapi.model.TareaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/lista_tareas")
public class TareaController implements ListaTareasApi {


    private ListaTareasApiDelegate listaTareasApiDelegate;

    public TareaController(ListaTareasApiDelegate listaTareasApiDelegate) {
        this.listaTareasApiDelegate = listaTareasApiDelegate;
    }

    @GetMapping
    public ResponseEntity<List<TareaDto>> verTareas() {

        return listaTareasApiDelegate.verTareas();
    }

    @PostMapping
    public ResponseEntity<TareaDto> crearTarea(@RequestBody TareaDto tareaDto ) {

        return listaTareasApiDelegate.crearTarea(tareaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TareaDto> eliminarTarea(@PathVariable Integer id ) {

        return listaTareasApiDelegate.eliminarTarea(id);

    }

    @PutMapping ("/{id}")
    public ResponseEntity<TareaDto> modificarTarea(@PathVariable Integer id,@RequestBody TareaDto tareaDto ) {
            return listaTareasApiDelegate.modificarTarea(id,tareaDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaDto> verTareaID(@PathVariable Integer id ) {
        return listaTareasApiDelegate.verTareaID(id);
    }

}

package com.API.listaTareas.Controller;


import com.API.listaTareas.Modelo.Tareas;
import com.API.listaTareas.Services.TareasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/listaTareas")
public class TareasController {

    @Autowired
    private TareasService tareasService;


    @GetMapping
    public ResponseEntity<List<Tareas>> verTareas() {

        List<Tareas> listaTareas = tareasService.obtenerTareas();
        return ResponseEntity.ok(listaTareas);
    }

    @PostMapping
    public ResponseEntity<String> crearTarea(@RequestBody Tareas tareas ) {

        return ResponseEntity.ok(tareasService.crearTarea(tareas));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTarea(@PathVariable long id ) {

        return ResponseEntity.ok(tareasService.eliminarTarea(id));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> modificarTarea(@PathVariable long id,@RequestBody Tareas tareas ) {

        return ResponseEntity.ok(tareasService.modificarTarea(id,tareas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verTareaID(@PathVariable long id ) {

        return ResponseEntity.ok(tareasService.obtenerTareaPorId(id));
    }

}

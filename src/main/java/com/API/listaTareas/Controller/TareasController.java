package com.API.listaTareas.Controller;


import com.API.listaTareas.Modelo.Tareas;
import com.API.listaTareas.Services.TareasService;
import com.API.listaTareas.dto.TareaDto;
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
    public ResponseEntity<List<TareaDto>> verTareas() {

        List<TareaDto> listaTareas = tareasService.obtenerTareas();
        return ResponseEntity.ok(listaTareas);
    }

    @PostMapping
    public ResponseEntity<String> crearTarea(@RequestBody TareaDto tareaDto ) {

        return ResponseEntity.ok(tareasService.crearTarea(tareaDto));
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

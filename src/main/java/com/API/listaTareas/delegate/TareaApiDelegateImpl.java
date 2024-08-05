package com.API.listaTareas.delegate;

//import com.API.listaTareas.dto.TareaDto;
import com.API.listaTareas.service.TareasService;
import com.baeldung.openapi.api.ListaTareasApiDelegate;
import com.baeldung.openapi.model.TareaDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class TareaApiDelegateImpl implements ListaTareasApiDelegate {


    private TareasService tareasService;

    public TareaApiDelegateImpl(TareasService tareasService) {
        this.tareasService = tareasService;
    }

    @Override
    public ResponseEntity<List<TareaDto>> verTareas() {

        return ResponseEntity.ok(tareasService.obtenerTareas());
    }

    @Override
    public ResponseEntity<TareaDto> crearTarea(@RequestBody TareaDto tareaDto ) {

        return ResponseEntity.ok(tareasService.crearTarea(tareaDto));
    }

    @Override
    public ResponseEntity<TareaDto> eliminarTarea(@PathVariable Integer id ) {

        return ResponseEntity.ok(tareasService.eliminarTarea(id));
    }

    @Override
    public ResponseEntity<TareaDto> modificarTarea(@PathVariable Integer id,@RequestBody TareaDto tareaDto ) {


            return ResponseEntity.ok(tareasService.modificarTarea(id, tareaDto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaDto> verTareaID(@PathVariable Integer id ) {


        try {
            TareaDto tarea = tareasService.obtenerTareaPorId(id);

            return ResponseEntity.ok(tarea);

        } catch (NoSuchElementException e) {

            return ResponseEntity.notFound().build();
        }
    }




}

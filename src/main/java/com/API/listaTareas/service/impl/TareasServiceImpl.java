package com.API.listaTareas.service.impl;

import com.API.listaTareas.model.Tarea;
import com.API.listaTareas.service.TareasService;
import com.API.listaTareas.repository.TareasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
//import java.util.Optional;

@Service
public class TareasServiceImpl implements TareasService {

    @Autowired
    private TareasRepository tareasRepository;


    @Override
    public List<Tarea> obtenerTareas() {

        return tareasRepository.findAll();

    }

    @Override
    public Tarea obtenerTareaPorId(Long id) {

        Optional<Tarea> tareaOptional = tareasRepository.findById(id);

        return tareaOptional.orElseThrow(() ->
                new NoSuchElementException("Tarea con el ID :" + id+ "no existe"));

    }

    @Override
    public String crearTarea(Tarea tarea) {
       tareasRepository.save(tarea);
       return "Tarea creada";

    }

    @Override
    public Boolean eliminarTarea(Long id) {

       return tareasRepository.findById(id).map(tarea -> {
            tareasRepository.delete(tarea);
            return true;
        }).orElse(false);

    }

    @Override
    public String modificarTarea(Long id, Tarea tarea) {

        Tarea modTarea = tareasRepository.findById(id).get();

     if (modTarea!= null)
      {

          if (tarea.getNombreTarea()!= null && !tarea.getNombreTarea().isEmpty())
            modTarea.setNombreTarea(tarea.getNombreTarea());

          if (tarea.getDescripcionTarea()!= null && !tarea.getDescripcionTarea().isEmpty())
              modTarea.setDescripcionTarea(tarea.getDescripcionTarea());

          if (tarea.getEstado()!= null && !tarea.getEstado().isEmpty())
              modTarea.setEstado(tarea.getEstado());

          if (tarea.getFechaFin()!= null && !tarea.getFechaFin().isEmpty())
              modTarea.setFechaFin(tarea.getFechaFin());

          if (tarea.getFechaInicio()!= null && !tarea.getFechaInicio().isEmpty())
              modTarea.setFechaInicio(tarea.getFechaInicio());

          if (tarea.getPersonaAsignada()!= null && !tarea.getPersonaAsignada().isEmpty())
              modTarea.setPersonaAsignada(tarea.getPersonaAsignada());


          tareasRepository.save(modTarea);
          return  "Tarea actualizada";

      }
      else {
          return "No se ha actualizado";
      }


    }
}
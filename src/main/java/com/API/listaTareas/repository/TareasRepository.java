package com.API.listaTareas.repository;

import com.API.listaTareas.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareasRepository extends JpaRepository <Tarea, Long> {
}

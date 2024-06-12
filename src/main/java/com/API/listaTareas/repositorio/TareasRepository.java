package com.API.listaTareas.repositorio;

import com.API.listaTareas.Modelo.Tareas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareasRepository extends JpaRepository <Tareas, Long> {
}

package com.API.listaTareas.controller;

import com.baeldung.openapi.api.ListaTareasApiDelegate;
import com.baeldung.openapi.model.TareaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TareaController.class)
class TareaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListaTareasApiDelegate listaTareasApiDelegate;

    @InjectMocks
    private TareaController tareaController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void verTareas() throws Exception {
        TareaDto tareaDto = new TareaDto();
        tareaDto.setId(1L);
        tareaDto.setDescripcion("Tarea de prueba");
        tareaDto.setCompletada(false);

        List<TareaDto> tareas = Arrays.asList(tareaDto);

        Mockito.when(listaTareasApiDelegate.verTareas()).thenReturn(ResponseEntity.ok(tareas));

        mockMvc.perform(get("/lista_tareas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].descripcion").value("Tarea de prueba"))
                .andExpect(jsonPath("$[0].completada").value(false));
    }

    @Test
    void crearTarea() throws Exception {
        TareaDto tareaDto = new TareaDto();
        tareaDto.setId(1L);
        tareaDto.setDescripcion("Tarea creada");
        tareaDto.setCompletada(false);

        Mockito.when(listaTareasApiDelegate.crearTarea(Mockito.any(TareaDto.class)))
                .thenReturn(ResponseEntity.ok(tareaDto));

        mockMvc.perform(post("/lista_tareas")
                .contentType("application/json")
                .content("{\"id\":1,\"descripcion\":\"Tarea creada\",\"completada\":false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descripcion").value("Tarea creada"))
                .andExpect(jsonPath("$.completada").value(false));
    }

    @Test
    void eliminarTarea() throws Exception {
        TareaDto tareaDto = new TareaDto();
        tareaDto.setId(1L);
        tareaDto.setDescripcion("Tarea eliminada");
        tareaDto.setCompletada(true);

        Mockito.when(listaTareasApiDelegate.eliminarTarea(Mockito.anyInt()))
                .thenReturn(ResponseEntity.ok(tareaDto));

        mockMvc.perform(delete("/lista_tareas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descripcion").value("Tarea eliminada"))
                .andExpect(jsonPath("$.completada").value(true));
    }

    @Test
    void modificarTarea() throws Exception {
        TareaDto tareaDto = new TareaDto();
        tareaDto.setId(1L);
        tareaDto.setDescripcion("Tarea modificada");
        tareaDto.setCompletada(true);

        Mockito.when(listaTareasApiDelegate.modificarTarea(Mockito.anyInt(), Mockito.any(TareaDto.class)))
                .thenReturn(ResponseEntity.ok(tareaDto));

        mockMvc.perform(put("/lista_tareas/1")
                .contentType("application/json")
                .content("{\"id\":1,\"descripcion\":\"Tarea modificada\",\"completada\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descripcion").value("Tarea modificada"))
                .andExpect(jsonPath("$.completada").value(true));
    }

    @Test
    void verTareaID() throws Exception {
        TareaDto tareaDto = new TareaDto();
        tareaDto.setId(1L);
        tareaDto.setDescripcion("Tarea de prueba por ID");
        tareaDto.setCompletada(false);

        Mockito.when(listaTareasApiDelegate.verTareaID(Mockito.anyInt())).thenReturn(ResponseEntity.ok(tareaDto));

        mockMvc.perform(get("/lista_tareas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descripcion").value("Tarea de prueba por ID"))
                .andExpect(jsonPath("$.completada").value(false));
    }
}
package com.API.listaTareas.controller;

import com.baeldung.openapi.model.UsuarioDTO;
import com.baeldung.openapi.api.UsuariosApiDelegate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UsuarioControllerTest {

    @Mock
    private UsuariosApiDelegate usuariosApiDelegate;

    @InjectMocks
    private UsuarioController usuarioController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    void crearUsuario() throws Exception {

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setNombre("Paco");
        usuarioDTO.setTelefono("123");

        Mockito.when(usuariosApiDelegate.crearUsuario(Mockito.any(UsuarioDTO.class))).thenReturn(ResponseEntity.ok(usuarioDTO));

        mockMvc.perform(post("/usuarios")
                .contentType("application/json")
                .content("{\"id\":1,\"nombre\":\"Paco\",\"telefono\":\"123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Paco"))
                .andExpect(jsonPath("$.telefono").value("123"));
    }

    @Test
    void eliminarUsuario() throws Exception {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setNombre("Paco");
        usuarioDTO.setTelefono("123");

        Mockito.when(usuariosApiDelegate.eliminarUsuario(1)).thenReturn(ResponseEntity.ok(usuarioDTO));

        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Paco"))
                .andExpect(jsonPath("$.telefono").value("123"));
    }

    @Test
    void modificarUsuario() throws Exception {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setNombre("Paco");
        usuarioDTO.setTelefono("123");
        Mockito.when(usuariosApiDelegate.modificarUsuario(Mockito.eq(1), Mockito.any(UsuarioDTO.class))).thenReturn(ResponseEntity.ok(usuarioDTO));

        mockMvc.perform(put("/usuarios/1")
                .contentType("application/json")
                .content("{\"id\":1,\"nombre\":\"Paco\",\"telefono\":\"123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Paco"))
                .andExpect(jsonPath("$.telefono").value("123"));
    }

    @Test
    void verUsuario() throws Exception {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setNombre("Paco");
        usuarioDTO.setTelefono("123");

        List<UsuarioDTO> usuarios = Arrays.asList(usuarioDTO);
        Mockito.when(usuariosApiDelegate.verUsuario()).thenReturn(ResponseEntity.ok(usuarios));

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Paco"))
                .andExpect(jsonPath("$[0].telefono").value("123"));
    }
}
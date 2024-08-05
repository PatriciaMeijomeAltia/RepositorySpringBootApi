package com.API.listaTareas.service.impl;

import com.API.listaTareas.exception.MensajeError;
import com.API.listaTareas.mapper.UsuarioMapper;
import com.API.listaTareas.model.Tarea;
import com.API.listaTareas.model.Usuario;
import com.API.listaTareas.repository.UsuarioRepository;
import com.API.listaTareas.service.UsuarioService;
import com.baeldung.openapi.model.UsuarioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;


    @BeforeEach
    void setUp() {

    }

    @Test
    void crearUser() {

        UsuarioDTO mockUsuarioDTO = new UsuarioDTO();
        mockUsuarioDTO.setId(1L);
        mockUsuarioDTO.setNombre("Juan");
        mockUsuarioDTO.setTelefono("123");

        Usuario mockUsuarioEntity = new Usuario();
        mockUsuarioEntity.setId(1);
        mockUsuarioEntity.setNombre("Juan");
        mockUsuarioEntity.setTelefono("123");

        // Configurar  mocks
        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(mockUsuarioEntity);

        // Llamar al método a probar
        UsuarioDTO resultado=usuarioServiceImpl.crearUser(mockUsuarioDTO);

        //Verificar llamada a save en el repositorio
        Mockito.verify(usuarioRepository).save(Mockito.any(Usuario.class));

        //resultado
        assertEquals(mockUsuarioDTO.getId(), resultado.getId());
        assertEquals(mockUsuarioDTO.getNombre(), resultado.getNombre());
        assertEquals(mockUsuarioDTO.getTelefono(), resultado.getTelefono());

    }

    @Test
    void eliminarUser() {

        UsuarioDTO mockUsuarioDTO = new UsuarioDTO();
        mockUsuarioDTO.setId(1L);
        mockUsuarioDTO.setNombre("Juan");
        mockUsuarioDTO.setTelefono("123");

        Usuario mockUsuarioEntity = new Usuario();
        mockUsuarioEntity.setId(1);
        mockUsuarioEntity.setNombre("Juan");
        mockUsuarioEntity.setTelefono("123");

        Mockito.when(usuarioRepository.findById(1)).thenReturn(Optional.of(mockUsuarioEntity));

        // Llamar al método a probar
        UsuarioDTO resultado = usuarioServiceImpl.eliminarUser(1);

        // Verificar llamada a delete en el repositorio
        Mockito.verify(usuarioRepository).findById(1);
        Mockito.verify(usuarioRepository).delete(mockUsuarioEntity);

        //resultado
        assertEquals(mockUsuarioDTO.getId(), resultado.getId());
        assertEquals(mockUsuarioDTO.getNombre(), resultado.getNombre());
        assertEquals(mockUsuarioDTO.getTelefono(), resultado.getTelefono());

    }

    @Test
    void modificarUser() {

        UsuarioDTO mockUsuarioDTO = new UsuarioDTO();
        mockUsuarioDTO.setId(1L);
        mockUsuarioDTO.setNombre("Paco");
        mockUsuarioDTO.setTelefono("456");

        Usuario mockUsuarioEntity = new Usuario();
        mockUsuarioEntity.setId(1);
        mockUsuarioEntity.setNombre("Juan");
        mockUsuarioEntity.setTelefono("123");

        // Usuario actualizado
        Usuario updatedUsuario = new Usuario();
        updatedUsuario.setId(1);
        updatedUsuario.setNombre("Paco");
        updatedUsuario.setTelefono("456");

        // Configurar  mocks
        Mockito.when(usuarioRepository.findById(1)).thenReturn(Optional.of(mockUsuarioEntity));
        Mockito.when(usuarioRepository.save(updatedUsuario)).thenReturn(updatedUsuario);

        // Llamar al método a probar
        UsuarioDTO resultado = usuarioServiceImpl.modificarUser(1, mockUsuarioDTO);

        // Verificar llamada a save en el repositorio
        Mockito.verify(usuarioRepository).findById(1);
        Mockito.verify(usuarioRepository).save(mockUsuarioEntity);

        //resultado
        assertEquals(mockUsuarioDTO.getId(), resultado.getId());
        assertEquals(mockUsuarioDTO.getNombre(), resultado.getNombre());
        assertEquals(mockUsuarioDTO.getTelefono(), resultado.getTelefono());

    }

    @Test
    void modificarUserException() {

        UsuarioDTO mockUsuarioDTO = new UsuarioDTO();
        mockUsuarioDTO.setId(1L);
        mockUsuarioDTO.setNombre("Paco");
        mockUsuarioDTO.setTelefono("456");


        // Configurar mocks vacio
        Mockito.when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        // Verificar que se lanza la excepción
        MensajeError excepcion=assertThrows(MensajeError.class, () -> usuarioServiceImpl.modificarUser(1, mockUsuarioDTO));

        // Verificar salida mensaje de la excepción
        assertEquals("No detectado: 1", excepcion.getMessage());

        // Verificar que no se llama a save
        Mockito.verify(usuarioRepository).findById(1);
        Mockito.verify(usuarioRepository, Mockito.never()).save(Mockito.any(Usuario.class));

    }


    @Test
    void mostrarUser() {

        UsuarioDTO mockUsuarioDTO = new UsuarioDTO();
        mockUsuarioDTO.setId(1L);
        mockUsuarioDTO.setNombre("Juan");
        mockUsuarioDTO.setTelefono("123");

        Usuario mockUsuarioEntity = new Usuario();
        mockUsuarioEntity.setId(1);
        mockUsuarioEntity.setNombre("Juan");
        mockUsuarioEntity.setTelefono("123");


        // Crear una lista de usuarios
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(mockUsuarioEntity);

        // Crear una lista de UsuarioDTO
        List<UsuarioDTO> listaUsuarioDTOs = new ArrayList<>();
        listaUsuarioDTOs.add(mockUsuarioDTO);

        // Configurar  mocks
        Mockito.when(usuarioRepository.findAll()).thenReturn(listaUsuarios);

        // Llamar al método a probar
        List<UsuarioDTO> resultado = usuarioServiceImpl.mostrarUser();

        //resultado
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(mockUsuarioDTO.getId(), resultado.get(0).getId());
        assertEquals(mockUsuarioDTO.getNombre(), resultado.get(0).getNombre());
        assertEquals(mockUsuarioDTO.getTelefono(), resultado.get(0).getTelefono());
    }

    @Test
    void mostrarUserException() {

        // Configurar  mocks vacio
        Mockito.when(usuarioRepository.findAll()).thenReturn(new ArrayList<>());

        // Verificar que se lanza la excepción
        MensajeError excepcion=assertThrows(MensajeError.class, () -> usuarioServiceImpl.mostrarUser());

        // Verificar salida mensaje de la excepción
        assertEquals("No hay usuarios disponibles", excepcion.getMessage());

    }
}
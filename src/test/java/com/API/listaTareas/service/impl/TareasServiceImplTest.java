package com.API.listaTareas.service.impl;

import com.API.listaTareas.exception.MensajeError;
import com.API.listaTareas.mapper.TareaMapper;
import com.API.listaTareas.model.Tarea;
import com.API.listaTareas.model.Usuario;
import com.API.listaTareas.repository.TareasRepository;
import com.baeldung.openapi.model.TareaDto;
import com.baeldung.openapi.model.UsuarioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TareasServiceImplTest {

    @Mock
    private TareasRepository tareasRepository;

    @Mock
    private TareaMapper tareaMapper;

    @InjectMocks
    private TareasServiceImpl tareasService;

    private TareaDto mocktareaDto;
    private Tarea mocktareaEntity;
    private UsuarioDTO mockUsuarioDTO;
    private Usuario mockUsuarioEntity;

    @BeforeEach
    void setUp() {

    }

    @Test
    void obtenerTareasException() {

        // Configurar  mocks vacio
        Mockito.when(tareasRepository.findAll()).thenReturn(new ArrayList<>());

        // Verificar que se lanza la excepción
        MensajeError excepcion=assertThrows(MensajeError.class, () -> tareasService.obtenerTareas());

        // Verificar salida mensaje de la excepción
        assertEquals("No se encontraron tareas", excepcion.getMessage());
    }


    @Test
    void obtenerTareas() {

        Usuario mockUsuarioEntity = new Usuario();
        mockUsuarioEntity.setId(1);
        mockUsuarioEntity.setNombre("Paco");
        mockUsuarioEntity.setTelefono("123");

        Tarea tarea1 = new Tarea(1, "Tarea 1", false, mockUsuarioEntity);
        Tarea tarea2 = new Tarea(2, "Tarea 2", true, mockUsuarioEntity);

        List<Tarea> mockTareas = Arrays.asList(tarea1, tarea2);

        // Configurar mock
        Mockito.when(tareasRepository.findAll()).thenReturn(mockTareas);

        // Llamar al método a probar
        List<TareaDto> resultado = tareasService.obtenerTareas();

        // Verificar el resultado
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Tarea 1", resultado.get(0).getDescripcion());
        assertEquals("Tarea 2", resultado.get(1).getDescripcion());

        // Verificar la llamada al repositorio
        Mockito.verify(tareasRepository).findAll();
    }

    @Test
    void obtenerTareaPorId() {

        Usuario mockUsuarioEntity = new Usuario();
        mockUsuarioEntity.setId(1);
        mockUsuarioEntity.setNombre("Paco");
        mockUsuarioEntity.setTelefono("123");

        Tarea mockTarea = new Tarea(1, "Tarea existente", false, mockUsuarioEntity);

        // Configurar el comportamiento del mock
        Mockito.when(tareasRepository.findById(1)).thenReturn(Optional.of(mockTarea));

        // Llamar al método a probar
        TareaDto resultado = tareasService.obtenerTareaPorId(1);

        // Verificar el resultado
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Tarea existente", resultado.getDescripcion());
        assertFalse(resultado.getCompletada());
        assertEquals(1L, resultado.getUsuario().getId());
        assertEquals("Paco", resultado.getUsuario().getNombre());

        // Verificar la llamada al repositorio
        Mockito.verify(tareasRepository).findById(1);

    }

    @Test
    void crearTarea() {

        // UsuarioDto y Usuario
        UsuarioDTO mockUsuarioDTO = new UsuarioDTO();
        mockUsuarioDTO.setId(1L);
        mockUsuarioDTO.setNombre("Paco");
        mockUsuarioDTO.setTelefono("123");

        Usuario mockUsuarioEntity = new Usuario();
        mockUsuarioEntity.setId(1);
        mockUsuarioEntity.setNombre("Paco");
        mockUsuarioEntity.setTelefono("123");

        // TareaDto y Tarea
        TareaDto mocktareaDto = new TareaDto();
        mocktareaDto.setId(1L);
        mocktareaDto.setDescripcion("Tarea");
        mocktareaDto.setCompletada(false);
        mocktareaDto.setUsuario(mockUsuarioDTO);

        Tarea mocktareaEntity = new Tarea();
        mocktareaEntity.setId(1);
        mocktareaEntity.setDescripcion("Tarea");
        mocktareaEntity.setCompletada(false);
        mocktareaEntity.setUsuario(mockUsuarioEntity);

        // Configurar  mocks
        Mockito.when(tareasRepository.save(mocktareaEntity)).thenReturn(mocktareaEntity);

        // Llamar al método a probar
        TareaDto resultado=tareasService.crearTarea(mocktareaDto);

        //Verificar llamada a save en el repositorio
        Mockito.verify(tareasRepository).save(mocktareaEntity);

        //resultado
        //assertNotNull(resultado);
        assertEquals(mocktareaDto.getId(), resultado.getId());
        assertEquals(mocktareaDto.getDescripcion(), resultado.getDescripcion());
        assertEquals(mocktareaDto.getCompletada(), resultado.getCompletada());
        assertEquals(mocktareaDto.getUsuario().getId(), resultado.getUsuario().getId());
        assertEquals(mocktareaDto.getUsuario().getNombre(), resultado.getUsuario().getNombre());
    }

    @Test
    void eliminarTarea() {


        // TareaDto y Tarea
        TareaDto mocktareaDto = new TareaDto();
        mocktareaDto.setId(1L);
        mocktareaDto.setDescripcion("Tarea");
        mocktareaDto.setCompletada(false);
        mocktareaDto.setUsuario(mockUsuarioDTO);

        Tarea mocktareaEntity = new Tarea();
        mocktareaEntity.setId(1);
        mocktareaEntity.setDescripcion("Tarea");
        mocktareaEntity.setCompletada(false);
        mocktareaEntity.setUsuario(mockUsuarioEntity);

        Mockito.when(tareasRepository.findById(1)).thenReturn(Optional.of(mocktareaEntity));

        // Llamar al método a probar
        TareaDto resultado = tareasService.eliminarTarea(1);

        // Verificar llamada a delete en el repositorio
        Mockito.verify(tareasRepository).findById(1);
        Mockito.verify(tareasRepository).delete(mocktareaEntity);

        //resultado
        assertEquals(mocktareaDto.getId(), resultado.getId());
        assertEquals(mocktareaDto.getCompletada(), resultado.getCompletada());
        assertEquals(mocktareaDto.getDescripcion(), resultado.getDescripcion());
    }

    @Test
    void eliminarTareaException() {

        // Configurar mock vacio
        Mockito.when(tareasRepository.findById(1)).thenReturn(Optional.empty());

        // Verificar  excepción
        MensajeError excepcion = assertThrows(MensajeError.class, () -> tareasService.eliminarTarea(1));

        // Verificar salida mensaje de la excepción
        assertEquals("Tarea no encontrada", excepcion.getMessage());

        // Verificar que no se llama a delete
        Mockito.verify(tareasRepository).findById(1);
        Mockito.verify(tareasRepository, Mockito.never()).delete(Mockito.any(Tarea.class));
    }

    @Test
    void modificarTarea() {


        // UsuarioDto y Usuario
        UsuarioDTO mockUsuarioDTO = new UsuarioDTO();
        mockUsuarioDTO.setId(1L);
        mockUsuarioDTO.setNombre("Paco");
        mockUsuarioDTO.setTelefono("123");

        Usuario mockUsuarioEntity = new Usuario();
        mockUsuarioEntity.setId(1);
        mockUsuarioEntity.setNombre("Paco");
        mockUsuarioEntity.setTelefono("123");

        // TareaDto y Tarea
        TareaDto mocktareaDto = new TareaDto();
        mocktareaDto.setId(1L);
        mocktareaDto.setDescripcion("Tarea");
        mocktareaDto.setCompletada(false);
        mocktareaDto.setUsuario(mockUsuarioDTO);

        Tarea mocktareaEntity = new Tarea();
        mocktareaEntity.setId(1);
        mocktareaEntity.setDescripcion("Tarea");
        mocktareaEntity.setCompletada(false);
        mocktareaEntity.setUsuario(mockUsuarioEntity);

        // tareaDto a modificar
        TareaDto modificarmocktareaDto = new TareaDto();
        modificarmocktareaDto.setId(1L);
        modificarmocktareaDto.setDescripcion("Nueva Tarea");
        modificarmocktareaDto.setCompletada(true);
        modificarmocktareaDto.setUsuario(mockUsuarioDTO);

        // tarea a modificar
        Tarea modifcarTarea = new Tarea();
        modifcarTarea.setId(1);
        modifcarTarea.setDescripcion("Nueva Tarea");
        modifcarTarea.setCompletada(true);
        modifcarTarea.setUsuario(mockUsuarioEntity);


        // Configurar  mocks
        Mockito.when(tareasRepository.findById(1)).thenReturn(Optional.of(mocktareaEntity));
        Mockito.when(tareasRepository.save(Mockito.any(Tarea.class))).thenReturn(modifcarTarea);

        // Llamar al método a probar
        TareaDto resultado = tareasService.modificarTarea(1,modificarmocktareaDto);
        System.out.println("Resultado: " + resultado);

        // Usar ArgumentCaptor para capturar el objeto pasado a save
        ArgumentCaptor<Tarea> tareaCaptor = ArgumentCaptor.forClass(Tarea.class);
        Mockito.verify(tareasRepository).findById(1);
        Mockito.verify(tareasRepository).save(tareaCaptor.capture());

        Tarea capturedTarea = tareaCaptor.getValue();
        assertNotNull(capturedTarea);
        assertEquals(modifcarTarea.getId(), capturedTarea.getId());
        assertEquals(modifcarTarea.getDescripcion(), capturedTarea.getDescripcion());
        assertEquals(modifcarTarea.getCompletada(), capturedTarea.getCompletada());
        assertEquals(modifcarTarea.getUsuario().getId(), capturedTarea.getUsuario().getId());
        assertEquals(modifcarTarea.getUsuario().getNombre(), capturedTarea.getUsuario().getNombre());


        // Verificar llamada a save en el repositorio
        Mockito.verify(tareasRepository).findById(1);
        Mockito.verify(tareasRepository).save(modifcarTarea);

        //resultado
        //assertNotNull(resultado);
        assertEquals(modificarmocktareaDto.getId(), resultado.getId());
        assertEquals(modificarmocktareaDto.getDescripcion(), resultado.getDescripcion());
        assertEquals(modificarmocktareaDto.getCompletada(), resultado.getCompletada());
        assertEquals(modificarmocktareaDto.getUsuario().getId(), resultado.getUsuario().getId());
        assertEquals(modificarmocktareaDto.getUsuario().getNombre(), resultado.getUsuario().getNombre());

    }
}
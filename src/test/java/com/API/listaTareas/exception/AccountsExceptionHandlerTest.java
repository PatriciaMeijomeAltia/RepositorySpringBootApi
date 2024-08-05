package com.API.listaTareas.exception;

import com.API.listaTareas.exception.modeloError.ErrorJson;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountsExceptionHandlerTest {


    @InjectMocks
    private AccountsExceptionHandler accountsExceptionHandler;

    public AccountsExceptionHandlerTest() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void handleUsuarioNotFoundException() {
        // Configuración del test
        String mensajeError = "Usuario no encontrado";
        MensajeError mensajeErrorExcepcion = new MensajeError(mensajeError);

        // WebRequest simulado
        WebRequest request = new ServletWebRequest(new MockHttpServletRequest());

        // Llamada al método a testear
        ResponseEntity<Object> responseEntity = accountsExceptionHandler.handleUsuarioNotFoundException(mensajeErrorExcepcion, request);

        // Verificación de resultados
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getStatusCode().value());
        ErrorJson errorJson = (ErrorJson) responseEntity.getBody();
        assertEquals(HttpStatus.NOT_FOUND.value(), errorJson.getCode());
        assertEquals(mensajeError, errorJson.getMessage());
    }

    @Test
    void handleAllExceptions() {

        // Simular una excepción
        Exception exception = new Exception("Mensaje de error de prueba");

        // Crear un mock de WebRequest
        WebRequest request = Mockito.mock(WebRequest.class);

        // Llamar al método handleAllExceptions
        ResponseEntity<Object> responseEntity = accountsExceptionHandler.handleAllExceptions(exception, request);

        // Verificar el estado de la respuesta
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

        // Verificar el cuerpo de la respuesta
        Object body = responseEntity.getBody();
        assertNotNull(body);
        assertTrue(body instanceof ErrorJson);

        ErrorJson errorJson = (ErrorJson) body;
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorJson.getCode());
        assertEquals("Mensaje de error de prueba", errorJson.getMessage());

        // Verificar encabezados
        HttpHeaders headers = responseEntity.getHeaders();
        assertNotNull(headers);

    }
}
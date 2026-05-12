package modelo;

import logica.SolicitudService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolicitudResponseTest {
    @Test
    void testConstructorYGetters() {
        SolicitudResponse response = new SolicitudResponse(true, 1234, "", true);

        assertTrue(response.isDone());
        assertEquals(1234, response.getTokenSolicitud());
        assertEquals("", response.getErrorMessage());
        assertTrue(response.isData());
    }
    @Test
    void probarGeneracionDeToken() {
        interfaz.SolicitudRepositorio mockRepo = org.mockito.Mockito.mock(interfaz.SolicitudRepositorio.class);
        SolicitudService solicitudService = new SolicitudService(mockRepo);
        Solicitud datosMock = new Solicitud();

        SolicitudResponse response = solicitudService.devolverToken("usuarioPrueba", datosMock);


        assertNotNull(response.getTokenSolicitud());
        assertTrue(response.getTokenSolicitud() >= 10000000);
        System.out.println("Token generado con éxito: " + response.getTokenSolicitud());
    }
}
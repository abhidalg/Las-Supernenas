package modelo;

import logica.Service;
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
        Service service = new Service();
        Solicitud datosMock = new Solicitud(); // Creamos datos vacíos para la prueba

        SolicitudResponse response = service.devolverToken("usuarioPrueba", datosMock);


        assertNotNull(response.getTokenSolicitud());
        assertTrue(response.getTokenSolicitud() >= 10000000);
        System.out.println("Token generado con éxito: " + response.getTokenSolicitud());
    }
}
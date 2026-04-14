package modelo;

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
}
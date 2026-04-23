/*import com.fasterxml.jackson.databind.ObjectMapper;
import logica.Service;
import modelo.Solicitud;
import modelo.SolicitudResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Controller.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Service service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void solicitar_ConDatosValidos_Retorna201() throws Exception {
        Solicitud solicitud = new Solicitud(
                Arrays.asList(0),
                Arrays.asList("Entidad1")
        );
        String jsonSolicitud = objectMapper.writeValueAsString(solicitud);

        SolicitudResponse respuestaSimulada = new SolicitudResponse(true, 12345678, null, true);

        Mockito.when(service.devolverToken(Mockito.eq("pepe"), Mockito.any(Solicitud.class)))
                .thenReturn(respuestaSimulada);

        mockMvc.perform(post("/Solicitud/Solicitar")
                        .param("nombreUsuario", "pepe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonSolicitud))
                .andExpect(status().isCreated()) // Verifica el 201 Created
                .andExpect(jsonPath("$.done").value(true))
                .andExpect(jsonPath("$.tokenSolicitud").value(12345678))
                .andExpect(jsonPath("$.data").value(true));
    }

    @Test
    public void solicitar_FaltaNombreUsuario_Retorna400() throws Exception {
        Solicitud solicitud = new Solicitud(
                Arrays.asList(0),
                Arrays.asList("Entidad1")
        );
        String jsonSolicitud = objectMapper.writeValueAsString(solicitud);


        mockMvc.perform(post("/Solicitud/Solicitar")
                        // Nota: Omitimos deliberadamente el .param("nombreUsuario", ...)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonSolicitud))
                .andExpect(status().isBadRequest()); // Verifica el 400 Bad Request automático de Spring
    }

    @Test
    public void solicitar_BodyInvalido_Retorna400() throws Exception {

        String jsonMalformado = "{ \"cantidadesIniciales\": \"esto no es un array\" }";

        mockMvc.perform(post("/Solicitud/Solicitar")
                        .param("nombreUsuario", "pepe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMalformado))
                .andExpect(status().isBadRequest()); // Verifica el 400 Bad Request
    }
}*/

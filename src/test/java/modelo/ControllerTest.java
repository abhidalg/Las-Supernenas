package modelo;

import com.fasterxml.jackson.databind.ObjectMapper;
import logica.Service;
import logica.Taller1Application;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Controller.class)
@ContextConfiguration(classes = Taller1Application.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Service service;

    @Autowired
    private ObjectMapper objectMapper;

    //solicitud/solicitar
    @Test
    public void solicitar_ConDatosValidos() throws Exception {
        Solicitud solicitud = new Solicitud(
                Arrays.asList(0),
                Arrays.asList("Entidad1")
        );
        String jsonSolicitud = objectMapper.writeValueAsString(solicitud);

        SolicitudResponse respuestaSimulada = new SolicitudResponse(true, 12345678, null, true);

        Mockito.when(service.devolverToken(Mockito.eq("pepe"), Mockito.any(Solicitud.class)))
                .thenReturn(respuestaSimulada);

        mockMvc.perform(get("/Solicitud/Solicitar")
                        .param("nombreUsuario", "pepe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.done").value(true));
    }

    @Test
    public void solicitar_FaltaNombreUsuario() throws Exception {
        Solicitud solicitud = new Solicitud(
                Arrays.asList(0),
                Arrays.asList("Entidad1")
        );
        String jsonSolicitud = objectMapper.writeValueAsString(solicitud);


        mockMvc.perform(get("/Solicitud/Solicitar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonSolicitud))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void solicitar_BodyInvalido() throws Exception {

        String jsonMalformado = "{ \"cantidadesIniciales\": \"esto no es un array\" }";

        mockMvc.perform(get("/Solicitud/Solicitar")
                        .param("nombreUsuario", "pepe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMalformado))
                .andExpect(status().isBadRequest());
    }
    // /Resultados
    @Test
    public void enviarResultado_Valido() throws Exception {
        String usuario = "Victoria";
        int tokenEncontrado = 14743362;

        SolicitudResponse mockResponse = new SolicitudResponse(true, tokenEncontrado, null, true);

        //Mockito.when(service.guardarResultado(Mockito.eq(usuario), Mockito.eq(tokenEncontrado)))
        //        .thenReturn(mockResponse);

        mockMvc.perform(post("/Resultados")
                        .param("nombreUsuario", usuario)
                        .param("tok", String.valueOf(tokenEncontrado)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.done").value(true))
                .andExpect(jsonPath("$.data").value(true));
    }
    @Test
    public void enviarResultado_TokenInvalido() throws Exception {
        mockMvc.perform(post("/Resultados")
                        .param("nombreUsuario", "Victoria")
                        .param("tok", "esto-no-es-un-numero"))
                .andExpect(status().isBadRequest());
    }
    // /Email
    @Test
    public void enviarEmail_Valido() throws Exception {
        String correo = "elisa@alumnos.urjc.es";
        String mensaje = "Su token ha sido generado con exito";

        SolicitudResponse mockResponse = new SolicitudResponse(true, 0, null, true);


        //Mockito.when(service.enviarEmail(Mockito.eq(correo), Mockito.eq(mensaje)))
        //        .thenReturn(mockResponse);

        mockMvc.perform(post("/Email")
                        .param("emailAddress", correo)
                        .param("message", mensaje))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.done").value(true))
                .andExpect(jsonPath("$.errorMessage").isEmpty());
    }
}

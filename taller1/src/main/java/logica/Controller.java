package logica;


import modelo.Solicitud;
import modelo.SolicitudResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController // Sustituye a @Component y @Path
public class Controller {

    private Service service = new Service();

    @PostMapping("/Solicitud/Solicitar") // Sustituye a @POST y @Path
    public SolicitudResponse solicitar(
            @RequestParam("nombreUsuario") String usuario, // Sustituye a @QueryParam
            @RequestBody Solicitud datos) {                // Necesario para leer el JSON de entrada

        SolicitudResponse respuesta = service.devolverToken(usuario, datos);

        System.out.println("========================================");
        System.out.println("  COMUNICACIÓN ESTABLECIDA CON: " + usuario);
        System.out.println("  TOKEN ENVIADO: " + respuesta.getTokenSolicitud());
        System.out.println("========================================");

        return respuesta;
    }
}

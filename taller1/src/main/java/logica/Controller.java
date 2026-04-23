package logica;
import modelo.Solicitud;
import modelo.SolicitudResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Solicitud")
public class Controller {

    private Service service = new Service();

    @GetMapping("/Solicitar")
    public SolicitudResponse solicitar(
            @RequestParam("nombreUsuario") String usuario,
            @RequestBody(required = false) Solicitud datos) {
        if (datos == null) {
            datos = new Solicitud();
        }
        return service.devolverToken(usuario, datos);
    }
}
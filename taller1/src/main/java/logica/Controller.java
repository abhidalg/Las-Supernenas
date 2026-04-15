package logica;

import jakarta.ws.rs.*;
import modelo.Solicitud;
import modelo.SolicitudResponse;



@Path("/")
public class Controller {
    private Service service=new Service();
    @POST
    @Path("/Solicitud/Solicitar")
    @Consumes("application/json")
    @Produces("application/json")
    public SolicitudResponse solicitar(@QueryParam("nombreUsuario") String usuario, Solicitud datos) {
        return service.devolverToken(usuario, datos);
    }
}

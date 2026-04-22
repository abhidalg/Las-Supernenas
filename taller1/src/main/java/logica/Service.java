package logica;

import modelo.Solicitud;
import modelo.SolicitudResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Clase de servicio encargada de la lógica de negocio del sistema.
 * Gestiona la generación de tokens aleatorios y el almacenamiento de resultados simulados.
 */
public class Service {
    private static Map<Integer, String> memoria = new HashMap<>();
    private Random random = new Random();

    /**
     * Genera un token aleatorio de 8 cifras y prepara la respuesta de la solicitud.
     * Almacena en memoria una cadena de resultado simulado asociada al token.
     * * @param usuario El nombre del usuario que realiza la petición.
     * @param datos Los datos técnicos de la solicitud inicial.
     * @return Una respuesta estructurada con el token de seguimiento y estado de éxito.
     */
    public SolicitudResponse devolverToken(String usuario, Solicitud datos) {
        int token = 10000000 + random.nextInt(90000000);
        String resultadoSimulado = "12\n0,7,4,red";
        memoria.put(token, resultadoSimulado);

        SolicitudResponse respuesta = new SolicitudResponse();
        respuesta.setDone(true);
        respuesta.setTokenSolicitud(token);
        respuesta.setData(true);
        respuesta.setErrorMessage(null);

        return respuesta;
    }
}

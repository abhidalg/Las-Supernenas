package logica;

import interfaz.SolicitudRepositorio;
import modelo.Solicitud;
import modelo.SolicitudEntidad;
import modelo.SolicitudResponse;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Clase de servicio encargada de la lógica de negocio del sistema.
 * Gestiona la generación de tokens aleatorios y el almacenamiento de resultados simulados.
 */
public class Service {
    private static Map<Integer, String> memoria = new HashMap<>();
    private Random random = new Random();

    private SolicitudRepositorio repositorio;

    /**
     * Genera un token aleatorio de 8 cifras y prepara la respuesta de la solicitud.
     * Almacena en memoria una cadena de resultado simulado asociada al token.
     * * @param usuario El nombre del usuario que realiza la petición.
     * @param datos Los datos técnicos de la solicitud inicial.
     * @return Una respuesta estructurada con el token de seguimiento y estado de éxito.
     */
    public SolicitudResponse devolverToken(String usuario, Solicitud datos) {
        int token = 10000000 + random.nextInt(90000000);
        String resultadoSimulado = generarGridAleatorio();
        memoria.put(token, resultadoSimulado);

        //GUARDARLO EN LA BASE DE DATOS
        SolicitudEntidad entidad = new SolicitudEntidad();
        entidad.setNombreUsuario(usuario);
        entidad.setToken(token);
        entidad.setResultadoData(resultadoSimulado);
        entidad.setDone(true);

        repositorio.save(entidad);

        SolicitudResponse respuesta = new SolicitudResponse();
        respuesta.setDone(true);
        respuesta.setTokenSolicitud(token);
        respuesta.setData(true);
        respuesta.setErrorMessage(null);

        return respuesta;
    }
    public String generarGridAleatorio() {
        int ancho = 12;
        int framesDeTiempo = 50;
        int puntosTotales = 1900;
        Random r = new Random();
        String[] colores = {"red", "green", "blue", "yellow"};
        String celdas = IntStream.range(0, puntosTotales)
                .mapToObj(i -> {
                    int t = r.nextInt(framesDeTiempo);
                    int y = r.nextInt(ancho);
                    int x = r.nextInt(ancho);
                    String color = colores[r.nextInt(colores.length)];
                    return new Object[] {t, y, x, color};
                })
                .sorted(Comparator.comparingInt(a -> (int) a[0]))
                .map(a -> String.format("%d,%d,%d,%s", a[0], a[1], a[2], a[3]))
                .collect(Collectors.joining("\n"));
        return ancho + "\n" + celdas;
    }
}

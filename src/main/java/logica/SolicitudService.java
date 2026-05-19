package logica;

import interfaz.SolicitudRepositorio;
import modelo.Solicitud;
import modelo.SolicitudEntidad;
import modelo.SolicitudResponse;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Servicio de la capa de negocio encargado de gestionar el procesamiento de las solicitudes
 */
@Service
public class SolicitudService {

    private final SolicitudRepositorio repositorio;
    private final Random random = new Random();

    public SolicitudService(SolicitudRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public SolicitudResponse devolverToken(String usuario, Solicitud datos) {
        if (datos == null) {
            datos = new Solicitud();
        }

        List<Integer> listaCantidades = datos.getCantidadesIniciales() != null
                ? datos.getCantidadesIniciales()
                : new ArrayList<>();

        // OBTENEMOS EL ÚLTIMO NÚMERO (Si la lista está vacía, por defecto usamos 0)
        int ultimoNumero = !listaCantidades.isEmpty()
                ? listaCantidades.get(listaCantidades.size() - 1)
                : 0;

        // OPERACIÓN MATEMÁTICA: El primer dígito del token será (ultimoNumero % 9) + 1
        long digitoInicial = (Math.abs(ultimoNumero) % 9) + 1;

        // El resto del token se genera con el tiempo actual (7 dígitos fijos)
        long restoTiempo = System.currentTimeMillis() % 10000000;
        String tiempoFormateado = String.format("%07d", Math.abs(restoTiempo));

        // Formamos el token uniendo el dígito inicial con el tiempo
        int token = Integer.parseInt(digitoInicial + tiempoFormateado);

        // Pasamos el token y el último número a la simulación
        String resultadoSimulado = generarEstelaRebotes(token, ultimoNumero);

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

    private String generarEstelaRebotes(int token, int ultimoNumero) {
        int ancho = 12;
        int frames = 51;
        String[] colores = {"red", "green", "purple", "yellow"};

        int[][] pos;

        // SELECCIÓN DE ESTADO POR MÚLTIPLOS DE 3 DEL ÚLTIMO NÚMERO
        int residuo = Math.abs(ultimoNumero) % 3;

        if (residuo == 0) {
            // ESTADO 1: Múltiplo exacto de 3 (Esquinas)
            System.out.println("🎲 Último número (" + ultimoNumero + ") es MÚLTIPLO DE 3. Activado: ESTADO 1 (Esquinas)");
            pos = new int[][]{{0, 1}, {0, ancho - 2}, {ancho - 1, 2}, {ancho - 1, ancho - 3}};
        } else if (residuo == 1) {
            // ESTADO 2: Múltiplo de 3 + 1 (Centro)
            System.out.println("🎲 Último número (" + ultimoNumero + ") residuo 1. Activado: ESTADO 2 (Centro)");
            pos = new int[][]{{5, 5}, {5, 6}, {6, 5}, {6, 6}};
        } else {
            // ESTADO 3: Múltiplo de 3 + 2 (Lineal)
            System.out.println("🎲 Último número (" + ultimoNumero + ") residuo 2. Activado: ESTADO 3 (Lineal)");
            pos = new int[][]{{6, 0}, {6, ancho - 1}, {0, 6}, {ancho - 1, 6}};
        }

        // Direcciones de movimiento iniciales diagonales
        int[][] dir = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        Map<String, String> gridAcumulado = new HashMap<>();
        List<String> celdas = new ArrayList<>();

        for (int c = 0; c < 4; c++) {
            gridAcumulado.put(pos[c][0] + "_" + pos[c][1], colores[c]);
        }

        celdas.addAll(gridAcumulado.entrySet().stream()
                .map(e -> "0," + e.getKey().replace("_", ",") + "," + e.getValue())
                .toList());

        for (int t = 1; t < frames; t++) {
            int[][] intentoPos = new int[4][2];
            for (int c = 0; c < 4; c++) {
                intentoPos[c][0] = pos[c][0] + dir[c][0];
                intentoPos[c][1] = pos[c][1] + dir[c][1];
            }

            for (int c = 0; c < 4; c++) {
                boolean huboChoque = false;

                if (intentoPos[c][0] < 0 || intentoPos[c][0] >= ancho ||
                        intentoPos[c][1] < 0 || intentoPos[c][1] >= ancho) {
                    huboChoque = true;
                }
                for (int o = 0; o < 4; o++) {
                    if (c == o) continue;
                    boolean directo = (intentoPos[c][0] == intentoPos[o][0] && intentoPos[c][1] == intentoPos[o][1]);
                    boolean cruzado = (intentoPos[c][0] == pos[o][0] && intentoPos[c][1] == pos[o][1] &&
                            intentoPos[o][0] == pos[c][0] && intentoPos[o][1] == pos[c][1]);
                    if (directo || cruzado) huboChoque = true;
                }

                if (huboChoque) {
                    int nuevaSuerte = random.nextInt(3);

                    if (nuevaSuerte == 0) {
                        dir[c][0] = random.nextBoolean() ? 1 : -1;
                        dir[c][1] = random.nextBoolean() ? 1 : -1;
                    } else if (nuevaSuerte == 1) {
                        dir[c][0] = 0;
                        dir[c][1] = random.nextBoolean() ? 1 : -1;
                    } else {
                        dir[c][0] = random.nextBoolean() ? 1 : -1;
                        dir[c][1] = 0;
                    }
                } else {
                    pos[c][0] = intentoPos[c][0];
                    pos[c][1] = intentoPos[c][1];
                }

                gridAcumulado.put(pos[c][0] + "_" + pos[c][1], colores[c]);
            }

            final int tiempoActual = t;
            celdas.addAll(gridAcumulado.entrySet().stream()
                    .map(e -> tiempoActual + "," + e.getKey().replace("_", ",") + "," + e.getValue())
                    .toList());
        }

        imprimirResultadosFinales(gridAcumulado);

        return ancho + "\n" + String.join("\n", celdas);
    }

    /**
     * Obtiene y procesa las métricas de la simulación para construir las propiedades del Grid.
     * filtra, transforma y recolecta las líneas de la estela en estructuras mapeadas de coordenadas
     *
     * @param token El identificador único numérico de la solicitud a consultar.
     * @return Un {@link Map} que contiene el conteo de líneas, el mapa de coordenadas con sus colores y el tiempo máximo
     */
    public Map<String, Object> obtenerDatosGrid(int token) {
        String datosGuardados = generarEstelaRebotes(token, token);
        String[] lineas = datosGuardados.split("\n");
        int count = Integer.parseInt(lineas[0].trim());

        Map<String, String> colors = Arrays.stream(lineas)
                .skip(1)
                .map(String::trim)
                .filter(l -> !l.isEmpty())
                .map(l -> l.split(","))
                .filter(p -> p.length >= 4)
                .collect(Collectors.toMap(
                        p -> p[0] + "-" + p[1] + "-" + p[2],
                        p -> p[3],
                        (existente, nuevo) -> nuevo
                ));

        int maxTime = Arrays.stream(lineas)
                .skip(1)
                .map(l -> l.split(","))
                .filter(p -> p.length >= 4)
                .mapToInt(p -> Integer.parseInt(p[0]))
                .max().orElse(0);

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("count", count);
        resultado.put("colors", colors);
        resultado.put("maxTime", maxTime);

        return resultado;
    }

    /**
     * Recupera la secuencia completa e histórica de la simulación de rebotes en formato de texto
     *
     * @param token El identificador único numérico de la solicitud
     * @return Una cadena de texto multilínea que representa la estela de la simulación
     */
    public String obtenerGridString(int token) {
        return generarEstelaRebotes(token, token);
    }

    /**
     * Agrupa y contabiliza las casillas totales conquistadas por cada color al final de la simulación,
     *
     * @param grid El mapa que contiene las posiciones y colores del tablero
     */
    private void imprimirResultadosFinales(Map<String, String> grid) {
        Map<String, Long> puntos = grid.values().stream()
                .collect(java.util.stream.Collectors.groupingBy(c -> c, java.util.stream.Collectors.counting()));

        System.out.println("\n🏁 --- PUNTUACIÓN FINAL (FRAME 50) ---");
        puntos.forEach((col, val) -> System.out.println(col.toUpperCase() + ": " + val + " casillas"));
        String win = puntos.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        System.out.println("🏆 GANADOR: " + win.toUpperCase() + "\n");
    }
}
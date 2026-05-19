package modelo;
/**
 * Clase que representa la respuesta con los resultados de una simulación o solicitud
 * Contiene el estado de finalización del proceso, el identificador único asociado
 * y la cadena de datos que describe el estado final del grid.
 */
public class ResultsResponse {

    /**
     * Indica si el procesamiento de los resultados se completo
     */
    private boolean done;
    /**
     * Identificador único de la solicitud consultada
     */
    private int tokenSolicitud;
    /**
     * Cadena de texto que contiene los datos de la simulación (estela de rebotes)
     */
    private String data;

    /**
     * Constructor por defecto de la clase ResultsResponse
     * Crea una instancia vacía
     */
    public ResultsResponse() {}

    /**
     * Constructor parametrizado para inicializar una respuesta completa de resultados
     *
     * @param done           {@code true} si la operación fue exitosa
     * @param tokenSolicitud El identificador único de la solicitud
     * @param data           Los datos resultantes en formato de texto
     */
    public ResultsResponse(boolean done, int tokenSolicitud, String data) {
        this.done = done;
        this.tokenSolicitud = tokenSolicitud;
        this.data = data;
    }
    /**
     * Comprueba si el procesamiento de los resultados finalizó correctamente
     *
     * @return {@code true} si se completó con éxito
     */
    public boolean isDone() { return done; }
    /**
     * Establece el estado de finalización de la operación.
     *
     * @param done {@code true} para marcar como finalizado con éxito
     */
    public void setDone(boolean done) { this.done = done; }
    /**
     * Obtiene el token de la solicitud
     *
     * @return El número entero que actúa como token de la solicitud
     */
    public int getTokenSolicitud() { return tokenSolicitud; }
    /**
     * Establece el token o identificador único para la solicitud
     *
     * @param tokenSolicitud El número entero que se asignara
     */
    public void setTokenSolicitud(int tokenSolicitud) { this.tokenSolicitud = tokenSolicitud; }
    /**
     * Obtiene los datos detallados de la simulación almacenados
     *
     * @return Un {@link String} con la traza de datos del grid, o {@code null} si no hay datos
     */
    public String getData() { return data; }
    /**
     * Establece los datos o la traza resultante de la simulación
     *
     * @param data La cadena de texto que contiene los datos
     */
    public void setData(String data) { this.data = data; }
}
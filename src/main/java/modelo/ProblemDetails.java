package modelo;

/**
 * Representa los detalles de un problema o error en la aplicación o API.
 * Esta clase se utiliza para proporcionar una respuesta de error estructurada
 * y estandarizada (generalmente basada en el estándar RFC 7807 para APIs HTTP),
 * ofreciendo al cliente información clara sobre el fallo ocurrido.
 */
public class ProblemDetails {
    /**
     * Una referencia URI que identifica el tipo de problema.
     */
    private String type;

    /**
     * Un resumen corto y legible por humanos sobre el tipo de problema.
     */
    private String title;

    /**
     * El código de estado HTTP asociado a esta ocurrencia específica del problema.
     */
    private Integer status;

    /**
     * Una explicación detallada y legible por humanos específica para esta ocurrencia del problema.
     */
    private String detail;

    /**
     * Una referencia URI que identifica la ocurrencia específica del problema.
     * Puede utilizarse para rastrear el error en los registros (logs) del servidor.
     */
    private String instance;

    /**
     * Constructor por defecto de la clase ProblemDetails.
     * Crea una instancia vacía sin inicializar los atributos.
     */
    public ProblemDetails() {}

    /**
     * Obtiene el URI que identifica el tipo de problema.
     *
     * @return Un {@link String} con el tipo de problema.
     */
    public String getType() { return type; }

    /**
     * Establece el URI que identifica el tipo de problema.
     *
     * @param type El tipo de problema a establecer.
     */
    public void setType(String type) { this.type = type; }

    /**
     * Obtiene el título o resumen corto del problema.
     *
     * @return Un {@link String} con el título del problema.
     */
    public String getTitle() { return title; }

    /**
     * Establece el título o resumen corto del problema.
     *
     * @param title El título del problema a establecer.
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * Obtiene el código de estado HTTP asociado al problema.
     *
     * @return Un {@link Integer} que representa el código de estado HTTP (por ejemplo, 400, 404, 500).
     */
    public Integer getStatus() { return status; }

    /**
     * Establece el código de estado HTTP para este problema.
     *
     * @param status El código de estado HTTP a establecer.
     */
    public void setStatus(Integer status) { this.status = status; }

    /**
     * Obtiene la descripción detallada del problema.
     *
     * @return Un {@link String} con los detalles del problema.
     */
    public String getDetail() { return detail; }

    /**
     * Establece la descripción detallada del problema.
     *
     * @param detail Los detalles específicos del problema a establecer.
     */
    public void setDetail(String detail) { this.detail = detail; }

    /**
     * Obtiene el URI que identifica la ocurrencia específica de este problema.
     *
     * @return Un {@link String} con la instancia del problema.
     */
    public String getInstance() { return instance; }

    /**
     * Establece el URI que identifica la ocurrencia específica de este problema.
     *
     * @param instance La instancia del problema a establecer.
     */
    public void setInstance(String instance) { this.instance = instance; }
}

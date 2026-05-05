package modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad de persistencia que representa la tabla "solicitudes" en la base de datos PostgreSQL.
 * Se diferencia del DTO {@link Solicitud} porque esta clase incluye los campos de control,
 * metadatos de base de datos e identificadores necesarios para la persistencia con JPA.
 */
@Entity
@Table(name = "solicitudes")
public class SolicitudEntidad {
    /**
     * Identificador único de la entidad.
     * Se genera de forma automática y autoincremental en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del usuario que realiza la solicitud.
     * Es un campo obligatorio en la base de datos.
     */
    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;

    /**
     * Token o identificador único numérico para la solicitud.
     * Este valor no puede ser nulo y debe ser único en la tabla.
     */
    @Column(unique = true, nullable = false)
    private Integer token;

    /**
     * Correo electrónico del usuario asociado a la solicitud.
     * Debe ser un valor único en la tabla.
     */
    @Column(unique = true)
    private String email;

    /**
     * Estado de la solicitud.
     * Indica si el procesamiento de la solicitud ha finalizado ({@code true}) o no ({@code false}).
     * Su valor por defecto es {@code false}.
     */
    private Boolean done = false;

    /**
     * Almacena los resultados del procesamiento de la solicitud.
     * En la base de datos se guarda como un tipo de dato "TEXT" para permitir cadenas largas (ej. JSON).
     */
    @Column(name = "resultado_data", columnDefinition = "TEXT")
    private String resultadoData;

    /**
     * Fecha y hora en la que se creó el registro de la solicitud.
     * Se inicializa por defecto con la fecha y hora del sistema en el momento de la instanciación.
     */
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    /**
     * Constructor por defecto requerido por JPA.
     * Crea una instancia vacía de la entidad.
     */
    public SolicitudEntidad() {
    }

    /**
     * Constructor parametrizado para inicializar una nueva entidad de solicitud.
     * Establece automáticamente el estado {@code done} a {@code false} y
     * la {@code fechaCreacion} al momento actual.
     *
     * @param nombreUsuario Nombre del usuario que hace la solicitud.
     * @param token         Token único numérico asignado a la solicitud.
     * @param email         Correo electrónico del usuario.
     * @param resultadoData Datos resultantes asociados a la solicitud.
     */
    public SolicitudEntidad(String nombreUsuario, Integer token, String email, String resultadoData) {
        this.nombreUsuario = nombreUsuario;
        this.token = token;
        this.email = email;
        this.resultadoData = resultadoData;
        this.done = false;
        this.fechaCreacion = LocalDateTime.now();
    }

    /**
     * Obtiene el identificador único de la base de datos.
     *
     * @return El identificador {@link Long} de la entidad.
     */
    public Long getId() { return id; }

    /**
     * Establece el identificador único de la entidad.
     *
     * @param id El identificador {@link Long} a establecer.
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Obtiene el nombre del usuario de la solicitud.
     *
     * @return Un {@link String} con el nombre de usuario.
     */
    public String getNombreUsuario() { return nombreUsuario; }

    /**
     * Establece el nombre del usuario para la solicitud.
     *
     * @param nombreUsuario El nombre de usuario a establecer.
     */
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    /**
     * Obtiene el token único de la solicitud.
     *
     * @return Un {@link Integer} con el token de la solicitud.
     */
    public Integer getToken() { return token; }

    /**
     * Establece el token único de la solicitud.
     *
     * @param token El número de token a establecer.
     */
    public void setToken(Integer token) { this.token = token; }

    /**
     * Obtiene el correo electrónico asociado a la solicitud.
     *
     * @return Un {@link String} con el correo electrónico.
     */
    public String getEmail() { return email; }

    /**
     * Establece el correo electrónico para esta solicitud.
     *
     * @param email El correo electrónico a establecer.
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Verifica si la solicitud ha sido procesada.
     *
     * @return {@code true} si está completada, {@code false} en caso contrario.
     */
    public Boolean getDone() { return done; }

    /**
     * Establece el estado de procesamiento de la solicitud.
     *
     * @param done {@code true} para marcarla como completada, {@code false} para pendiente.
     */
    public void setDone(Boolean done) { this.done = done; }

    /**
     * Obtiene los datos de resultado asociados a la solicitud.
     *
     * @return Un {@link String} con los datos resultantes.
     */
    public String getResultadoData() { return resultadoData; }

    /**
     * Establece los datos de resultado de la solicitud.
     *
     * @param resultadoData La cadena de texto con los datos a almacenar.
     */
    public void setResultadoData(String resultadoData) { this.resultadoData = resultadoData; }

    /**
     * Obtiene la fecha y hora en que se creó la entidad.
     *
     * @return Un objeto {@link LocalDateTime} que representa la fecha de creación.
     */
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    /**
     * Establece la fecha y hora de creación de la entidad.
     *
     * @param fechaCreacion La fecha y hora a establecer.
     */
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
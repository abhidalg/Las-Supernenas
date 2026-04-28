package modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad de persistencia que representa la tabla "solicitudes" en PostgreSQL.
 * Se diferencia del DTO 'Solicitud' porque incluye campos de control de base de datos.
 */
@Entity
@Table(name = "solicitudes")
public class SolicitudEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;

    @Column(unique = true, nullable = false)
    private Integer token;

    @Column(unique = true)
    private String email;

    private Boolean done = false;

    @Column(name = "resultado_data", columnDefinition = "TEXT")
    private String resultadoData;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    public SolicitudEntidad() {
    }

    public SolicitudEntidad(String nombreUsuario, Integer token, String email, String resultadoData) {
        this.nombreUsuario = nombreUsuario;
        this.token = token;
        this.email = email;
        this.resultadoData = resultadoData;
        this.done = false;
        this.fechaCreacion = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public Integer getToken() { return token; }
    public void setToken(Integer token) { this.token = token; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Boolean getDone() { return done; }
    public void setDone(Boolean done) { this.done = done; }

    public String getResultadoData() { return resultadoData; }
    public void setResultadoData(String resultadoData) { this.resultadoData = resultadoData; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
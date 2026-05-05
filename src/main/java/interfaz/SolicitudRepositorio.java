package interfaz;

import modelo.SolicitudEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio de Spring Data JPA para la entidad {@link SolicitudEntidad}.
 * Proporciona los métodos estándar para realizar operaciones CRUD (Crear, Leer, Actualizar, Borrar)
 * en la base de datos, así como consultas personalizadas.
 */
@Repository
public interface SolicitudRepositorio extends JpaRepository<SolicitudEntidad, Long> {
    /**
     * Busca y recupera una solicitud en la base de datos utilizando su token único.
     * Al devolver un {@link Optional}, permite manejar de forma segura los casos en los que
     * no exista ninguna solicitud asociada a dicho token.
     *
     * @param token El identificador numérico único de la solicitud que se desea buscar.
     * @return Un objeto {@link Optional} que contiene la {@link SolicitudEntidad} si se encuentra,
     *         o un {@link Optional#empty()} si no existe ningún registro con ese token.
     */
    Optional<SolicitudEntidad> findByToken(Integer token);
}
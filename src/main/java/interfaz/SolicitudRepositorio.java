package interfaz;

import modelo.SolicitudEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SolicitudRepositorio extends JpaRepository<SolicitudEntidad, Long> {
    Optional<SolicitudEntidad> findByToken(Integer token);
}
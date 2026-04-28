package interfaz;

import modelo.SolicitudEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudRepositorio extends JpaRepository<SolicitudEntidad, Long> { }
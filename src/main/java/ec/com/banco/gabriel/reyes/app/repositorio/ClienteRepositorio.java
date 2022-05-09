package ec.com.banco.gabriel.reyes.app.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.banco.gabriel.reyes.app.modelo.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findByCodigoCliente(Long codigoCliente);
	
	Optional<Cliente> findByIdentificacion(String identificacion);
	
//	@Query("SELECT c FROM Cliente c WHERE c.identificacion = :identificacion ")
//	Cliente obtenerPorIdentificacion(@Param("identificacion") String identificacion);
	
}

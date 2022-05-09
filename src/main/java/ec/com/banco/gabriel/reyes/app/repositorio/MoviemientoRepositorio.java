package ec.com.banco.gabriel.reyes.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ec.com.banco.gabriel.reyes.app.modelo.Cuenta;
import ec.com.banco.gabriel.reyes.app.modelo.Movimiento;

@Repository
public interface MoviemientoRepositorio extends JpaRepository<Movimiento, Long> {

	@Query("SELECT MAX(m.codigoMovimiento) FROM Movimiento m WHERE m.cuenta = :cuenta ")
	Long obtenerCodigoUltimoMovimiento(@Param("cuenta") Cuenta cuenta);
}

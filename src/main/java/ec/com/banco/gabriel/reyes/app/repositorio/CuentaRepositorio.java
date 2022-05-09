package ec.com.banco.gabriel.reyes.app.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.banco.gabriel.reyes.app.modelo.Cuenta;

@Repository
public interface CuentaRepositorio extends JpaRepository<Cuenta, Long> {

	Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

}

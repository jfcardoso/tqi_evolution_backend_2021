package br.com.jfcardoso.tqi_evolution_avaliacao.repositoy;

import br.com.jfcardoso.tqi_evolution_avaliacao.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepositoy extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

}

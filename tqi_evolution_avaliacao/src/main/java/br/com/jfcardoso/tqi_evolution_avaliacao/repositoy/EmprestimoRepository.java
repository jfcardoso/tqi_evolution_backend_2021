package br.com.jfcardoso.tqi_evolution_avaliacao.repositoy;

import br.com.jfcardoso.tqi_evolution_avaliacao.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findAllByClienteId(long clienteId);

    Emprestimo findByIdAndClienteId(long emprestimoId, long clienteId);

}

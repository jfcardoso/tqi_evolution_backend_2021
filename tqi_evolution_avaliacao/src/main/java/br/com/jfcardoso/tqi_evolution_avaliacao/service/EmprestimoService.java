package br.com.jfcardoso.tqi_evolution_avaliacao.service;

import br.com.jfcardoso.tqi_evolution_avaliacao.dto.RequestEmprestimoDTO;
import br.com.jfcardoso.tqi_evolution_avaliacao.dto.ResponseDetalhamentoEmprestimoDTO;
import br.com.jfcardoso.tqi_evolution_avaliacao.dto.ResponseListaEmprestimoDTO;
import br.com.jfcardoso.tqi_evolution_avaliacao.model.Cliente;
import br.com.jfcardoso.tqi_evolution_avaliacao.model.Emprestimo;
import br.com.jfcardoso.tqi_evolution_avaliacao.repositoy.EmprestimoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    //retorna a listagem dos empréstimos solicitados pelo cliente.
    public List<ResponseListaEmprestimoDTO> getEmprestimosByCliente(long clienteId) {
        List<Emprestimo> emprestimos = emprestimoRepository.findAllByClienteId(clienteId);
        return emprestimos.stream()
                .map(e -> ResponseListaEmprestimoDTO.builder()
                        .id(e.getId())
                        .qntParcelas(e.getQntParcelas())
                        .valor(e.getValor())
                        .build()
                ).collect(Collectors.toList());
    }

    public ResponseDetalhamentoEmprestimoDTO novoEmprestimo(RequestEmprestimoDTO emprestimoDTO, Cliente cliente) {
        validarEmprestimo(emprestimoDTO);
        Emprestimo emprestimo = emprestimoRepository.save(emprestimoDTO.builderEmprestimo(cliente));
        return buiderEmprestimoDTO(emprestimo);
    }

    //retorna o detalhe do empréstimo consultado pelo cliente
    public ResponseDetalhamentoEmprestimoDTO getEmprestimoDetalhado(long emprestimoId, long clienteId) {
        Emprestimo emprestimo = emprestimoRepository.findByIdAndClienteId(emprestimoId, clienteId);
        return buiderEmprestimoDTO(emprestimo);
    }

    private ResponseDetalhamentoEmprestimoDTO buiderEmprestimoDTO(Emprestimo emprestimo) {
        return ResponseDetalhamentoEmprestimoDTO.builder()
                .id(emprestimo.getId())
                .valor(emprestimo.getValor())
                .qntParcelas(emprestimo.getQntParcelas())
                .dataPrimeiraParcela(emprestimo.getDataPrimeiraParcela())
                .emailCliente(emprestimo.getCliente().getEmail())
                .rendaCliente(emprestimo.getCliente().getRenda())
                .build();
    }

    private void validarEmprestimo(RequestEmprestimoDTO emprestimoDTO){
        if(LocalDate.now().plusMonths(3).isBefore(emprestimoDTO.getDataPrimeiraParcela())){
            throw new RuntimeException("A data da primeira parcela não pode exceder os três meses.");
        }
    }
}

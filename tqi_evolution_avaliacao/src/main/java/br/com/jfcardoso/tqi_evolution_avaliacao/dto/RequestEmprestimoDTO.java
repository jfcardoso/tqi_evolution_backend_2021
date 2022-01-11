package br.com.jfcardoso.tqi_evolution_avaliacao.dto;

import br.com.jfcardoso.tqi_evolution_avaliacao.model.Cliente;
import br.com.jfcardoso.tqi_evolution_avaliacao.model.Emprestimo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RequestEmprestimoDTO {

    private long id;
    private BigDecimal valor;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataPrimeiraParcela;
    private int qntParcelas;


    public Emprestimo builderEmprestimo(Cliente cliente){

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setValor(this.valor);
        emprestimo.setCliente(cliente);
        emprestimo.setDataPrimeiraParcela(this.dataPrimeiraParcela);
        emprestimo.setQntParcelas(this.qntParcelas);
        return emprestimo;
    }
}

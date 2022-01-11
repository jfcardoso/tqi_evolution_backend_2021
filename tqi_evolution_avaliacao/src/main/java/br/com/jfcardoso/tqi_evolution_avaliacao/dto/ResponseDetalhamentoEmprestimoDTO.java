package br.com.jfcardoso.tqi_evolution_avaliacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ResponseDetalhamentoEmprestimoDTO {

    private long id;
    private BigDecimal valor;
    private int qntParcelas;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataPrimeiraParcela;
    private String emailCliente;
    private BigDecimal rendaCliente;

}

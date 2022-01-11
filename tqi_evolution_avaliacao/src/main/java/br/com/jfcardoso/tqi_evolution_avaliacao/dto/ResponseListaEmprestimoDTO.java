package br.com.jfcardoso.tqi_evolution_avaliacao.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ResponseListaEmprestimoDTO {

    private long id;
    private BigDecimal valor;
    private int qntParcelas;

}

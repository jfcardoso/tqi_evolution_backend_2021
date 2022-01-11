package br.com.jfcardoso.tqi_evolution_avaliacao.dto;


import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import java.math.BigDecimal;

@Data
@Builder
public class ResponseClienteDTO {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String rg;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;
    private BigDecimal renda;

}

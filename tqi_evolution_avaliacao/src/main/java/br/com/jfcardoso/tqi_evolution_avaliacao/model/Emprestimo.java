package br.com.jfcardoso.tqi_evolution_avaliacao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private BigDecimal valor;

    @Column
    private LocalDate dataPrimeiraParcela;

    @Column
    @Min(value=1)
    @Max(value = 60)
    private int qntParcelas;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

}

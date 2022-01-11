package br.com.jfcardoso.tqi_evolution_avaliacao.dto;

import br.com.jfcardoso.tqi_evolution_avaliacao.model.Cliente;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ResquestClienteDTO {

    private String nome;
    private String email;
    private String cpf;
    private String rg;
    private String senha;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;
    private BigDecimal renda;


    public Cliente builderCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setBairro(this.bairro);
        cliente.setCidade((this.cidade));
        cliente.setCpf(this.cpf);
        cliente.setEmail(this.email);
        cliente.setRg(this.rg);
        cliente.setRenda(this.renda);
        cliente.setEndereco(this.endereco);
        cliente.setSenha(this.senha);
        cliente.setEstado(this.estado);

        return cliente;
    }

}

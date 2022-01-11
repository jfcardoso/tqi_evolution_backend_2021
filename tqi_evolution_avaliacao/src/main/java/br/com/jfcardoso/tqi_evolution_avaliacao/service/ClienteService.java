package br.com.jfcardoso.tqi_evolution_avaliacao.service;

import br.com.jfcardoso.tqi_evolution_avaliacao.dto.ResponseClienteDTO;
import br.com.jfcardoso.tqi_evolution_avaliacao.dto.ResponseDetalhamentoEmprestimoDTO;
import br.com.jfcardoso.tqi_evolution_avaliacao.dto.ResponseListaEmprestimoDTO;
import br.com.jfcardoso.tqi_evolution_avaliacao.dto.ResquestClienteDTO;
import br.com.jfcardoso.tqi_evolution_avaliacao.model.Cliente;
import br.com.jfcardoso.tqi_evolution_avaliacao.repositoy.ClienteRepositoy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepositoy clienteRepositoy;

    private EmprestimoService emprestimoService;

    public ClienteService(ClienteRepositoy clienteRepositoy, EmprestimoService emprestimoService) {
        this.clienteRepositoy = clienteRepositoy;
        this.emprestimoService = emprestimoService;
    }

    public ResponseClienteDTO findByEmail(String email) {
        Optional<Cliente> clienteOptional = clienteRepositoy.findByEmail(email);
        if (!clienteOptional.isPresent()) {
            throw new RuntimeException("Cliente não encontrado.");
        }
        Cliente cliente = clienteOptional.get();
        return ResponseClienteDTO.builder()
                .id(cliente.getId())
                .bairro(cliente.getBairro())
                .cpf(cliente.getCpf())
                .cidade(cliente.getCidade())
                .email(cliente.getEmail())
                .endereco(cliente.getEndereco())
                .estado(cliente.getEstado())
                .nome(cliente.getNome())
                .rg(cliente.getRg())
                .renda(cliente.getRenda())
                .build();
    }

    public Cliente findByUserName(String userName) {
        Optional<Cliente> clienteOptional = clienteRepositoy.findByEmail(userName);
        if (!clienteOptional.isPresent()) {
            throw new RuntimeException("Cliente não encontrado.");
        }
        return clienteOptional.get();
    }

    public ResponseClienteDTO salvarCliente(ResquestClienteDTO clienteDTO) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        clienteDTO.setSenha(encoder.encode(clienteDTO.getSenha()));
        Cliente cliente = clienteRepositoy.save(clienteDTO.builderCliente());

        return builderClienteDTO(cliente);
    }

    private ResponseClienteDTO builderClienteDTO (Cliente cliente){
       return ResponseClienteDTO.builder()
               .id(cliente.getId())
               .nome(cliente.getNome())
               .cpf(cliente.getCpf())
               .rg(cliente.getRg())
               .email(cliente.getEmail())
               .endereco(cliente.getEndereco())
               .estado(cliente.getEstado())
               .renda(cliente.getRenda())
               .cidade(cliente.getCidade())
               .bairro(cliente.getBairro())
               .build();
    }
}

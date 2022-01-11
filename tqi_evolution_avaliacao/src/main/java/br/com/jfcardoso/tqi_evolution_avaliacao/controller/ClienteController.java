package br.com.jfcardoso.tqi_evolution_avaliacao.controller;

import br.com.jfcardoso.tqi_evolution_avaliacao.dto.ResponseClienteDTO;
import br.com.jfcardoso.tqi_evolution_avaliacao.dto.ResponseListaEmprestimoDTO;
import br.com.jfcardoso.tqi_evolution_avaliacao.dto.ResquestClienteDTO;
import br.com.jfcardoso.tqi_evolution_avaliacao.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseClienteDTO getCliente(Principal principal) {
        return clienteService.findByEmail(principal.getName());
    }

    @PostMapping
    public ResponseClienteDTO cadastrarCliente(@RequestBody @Valid ResquestClienteDTO clienteDTO) {
        return clienteService.salvarCliente(clienteDTO);
    }

}

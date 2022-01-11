package br.com.jfcardoso.tqi_evolution_avaliacao.controller;


import br.com.jfcardoso.tqi_evolution_avaliacao.dto.RequestEmprestimoDTO;
import br.com.jfcardoso.tqi_evolution_avaliacao.dto.ResponseDetalhamentoEmprestimoDTO;
import br.com.jfcardoso.tqi_evolution_avaliacao.dto.ResponseListaEmprestimoDTO;
import br.com.jfcardoso.tqi_evolution_avaliacao.model.Cliente;
import br.com.jfcardoso.tqi_evolution_avaliacao.service.ClienteService;
import br.com.jfcardoso.tqi_evolution_avaliacao.service.EmprestimoService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private EmprestimoService emprestimoService;
    private ClienteService clienteService;

    public EmprestimoController(EmprestimoService emprestimoService, ClienteService clienteService){
        this.emprestimoService = emprestimoService;
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    public ResponseDetalhamentoEmprestimoDTO getEmprestimoDetails(@PathVariable long id, Principal principal) {
        Cliente cliente = clienteService.findByUserName(principal.getName());
        return emprestimoService.getEmprestimoDetalhado(id, cliente.getId());
    }

    @GetMapping
    public List<ResponseListaEmprestimoDTO> getEmprestimos(Principal principal) {
        Cliente cliente = clienteService.findByUserName(principal.getName());
        return emprestimoService.getEmprestimosByCliente(cliente.getId());
    }

    @PostMapping
    public ResponseDetalhamentoEmprestimoDTO novoEmprestimo(Principal principal, @RequestBody RequestEmprestimoDTO emprestimoDTO) {
        Cliente cliente = clienteService.findByUserName(principal.getName());
        return emprestimoService.novoEmprestimo(emprestimoDTO, cliente);
    }

}

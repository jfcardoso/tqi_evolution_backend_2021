package br.com.jfcardoso.tqi_evolution_avaliacao.config.security;

import br.com.jfcardoso.tqi_evolution_avaliacao.model.Cliente;
import br.com.jfcardoso.tqi_evolution_avaliacao.repositoy.ClienteRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private ClienteRepositoy clienteRepositoy;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Optional<Cliente> cliente = clienteRepositoy.findByEmail(username);
       if (cliente.isPresent()){
           return cliente.get();
       }
       throw new UsernameNotFoundException("E-mail e/ou senha inválido(s)!");
    }

}

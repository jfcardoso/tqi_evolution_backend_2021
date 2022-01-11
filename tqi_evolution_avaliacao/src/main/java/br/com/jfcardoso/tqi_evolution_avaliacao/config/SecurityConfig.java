package br.com.jfcardoso.tqi_evolution_avaliacao.config;

import br.com.jfcardoso.tqi_evolution_avaliacao.config.security.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AutenticacaoService autenticacaoService;

    public SecurityConfig(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @Override //configurações de autenticação(controle de acesso, login)
    protected void configure(AuthenticationManagerBuilder autenticacao) throws Exception {
        autenticacao.userDetailsService(autenticacaoService)
                .passwordEncoder(new BCryptPasswordEncoder()); //encriptografa a senha
    }

    @Override //configurações de autorizações(perfil de acesso, urls)
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers(HttpMethod.POST,"/clientes").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
        // necessário para que o console H2 funcione
        http.headers().frameOptions().disable();
        http.csrf().disable();
    }

    @Override // configurações de recursos estáticos(requisições para arquivos: css, JS, imagens)
    public void configure(WebSecurity web) throws Exception {

    }

}

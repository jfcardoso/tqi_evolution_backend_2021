package br.com.jfcardoso.tqi_evolution_avaliacao.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
public class Cliente implements UserDetails {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column(unique = true)
    @Email(message = "Digite um e-mail válido!")
    private String email;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String rg;
    @Column
    private String senha;
    @Column
    private String endereco;
    @Column
    private String bairro;
    @Column
    private String cidade;
    @Column(length = 2)
    private String estado;
    @Column
    private BigDecimal renda;

    //quando carregar o usuário, carrego a lista de perfis dele.
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

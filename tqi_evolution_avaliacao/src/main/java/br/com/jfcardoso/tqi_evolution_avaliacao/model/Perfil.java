package br.com.jfcardoso.tqi_evolution_avaliacao.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String tipoPerfil; //se é cliente, admin, manutenção, etc.

    @Override
    public String getAuthority() {
        return tipoPerfil;
    }
}

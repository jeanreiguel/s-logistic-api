package br.com.senai.api.model;

import br.com.senai.domain.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
public class RoleDTO {
    private String nomeRole;

    private List<UsuarioDTO> usuarios;
}

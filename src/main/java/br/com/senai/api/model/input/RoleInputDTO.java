package br.com.senai.api.model.input;

import br.com.senai.domain.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleInputDTO {
    @Id
    private String nomeRole;

    @ManyToMany
    private List<Usuario> usuarios;
}

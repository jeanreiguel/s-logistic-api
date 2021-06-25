package br.com.senai.api.input;

import br.com.senai.domain.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class UsuarioInputDTO {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

   private List<Role> roles;
}

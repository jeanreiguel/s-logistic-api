package br.com.senai.api.model.input;

import br.com.senai.api.model.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PessoaInputDTO {

    @Valid
    @NotBlank
    String nome;

    @NotNull
    private UsuarioDTO usuario;

    @Valid
    @NotBlank
    String telefone;
}

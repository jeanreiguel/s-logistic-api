package br.com.senai.api.model.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PessoaInput {

    @Valid
    @NotBlank
    String nome;

    @Valid
    @NotBlank
    String email;

    @Valid
    @NotBlank
    String telefone;
}

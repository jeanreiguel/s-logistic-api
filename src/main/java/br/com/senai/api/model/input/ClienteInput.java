package br.com.senai.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClienteInput {

    @Valid
    @NotNull
    long id;
}

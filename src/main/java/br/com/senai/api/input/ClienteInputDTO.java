package br.com.senai.api.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClienteInputDTO {

    @Valid
    @NotNull
    long id;
}

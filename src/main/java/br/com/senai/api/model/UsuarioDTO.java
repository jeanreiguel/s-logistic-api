package br.com.senai.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioDTO {

    private String email;
    private String senha;
//    private List<RoleDTO> roles;
}

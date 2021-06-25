package br.com.senai.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "role_usuarios")
public class RoleUser {

    @Id
    @Column(name = "usuarios_id")
    private Long id;

    @Column(name = "role_nome_role")
    private String role;
}


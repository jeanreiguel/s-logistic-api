package br.com.senai.api.controller;

import br.com.senai.api.assembler.RoleAssembler;
import br.com.senai.api.model.RoleDTO;
import br.com.senai.api.model.input.RoleInputDTO;
import br.com.senai.domain.model.Role;
import br.com.senai.domain.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;
    private RoleAssembler roleAssembler;

    @PostMapping
    public RoleDTO incluir(@RequestBody RoleInputDTO roleInput) {
        Role role = roleAssembler.toEntity(roleInput);
        roleService.cadastrar(role);
        RoleDTO roledto = roleAssembler.toDTO(role);
        return roledto;
    }

    @GetMapping
    public List<RoleDTO> listar() {
        return roleService.listar();
    }
    @PutMapping
    public RoleDTO editar() {
        return null;
    }
    @DeleteMapping
    public void deletar(@PathVariable String role) {
        roleService.excluir(role);
    }
}

package br.com.senai.domain.service;


import br.com.senai.api.assembler.RoleAssembler;
import br.com.senai.api.model.RoleDTO;
import br.com.senai.domain.model.Role;
import br.com.senai.domain.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    private RoleRepository roleRepository;
    private RoleAssembler roleAssembler;

    public List<RoleDTO> listar() { return roleAssembler.toCollection(roleRepository.findAll()); }

    public Role cadastrar(Role role) {
        return roleRepository.save(role);
    }

    public void excluir(String role) {
        roleRepository.deleteById(role);
    }

    public ResponseEntity<RoleDTO> editar(String name_role, Role role) {

        if(!roleRepository.existsById(name_role)) {
            return ResponseEntity.notFound().build();
        }
        role.setNomeRole(name_role);
        roleRepository.save(role);
        RoleDTO roleDTO = roleAssembler.toDTO(role);

        return ResponseEntity.ok(roleDTO);
    }
}

package br.com.senai.domain.service;


import br.com.senai.api.assembler.RoleAssembler;
import br.com.senai.api.model.RoleDTO;
import br.com.senai.domain.model.Role;
import br.com.senai.domain.repository.RoleRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class RoleService {

    private RoleRepository roleRepository;
    private RoleAssembler roleAssembler;

    public List<RoleDTO> listar() {
        return roleAssembler.toCollection(roleRepository.findAll());
    }
    public Role cadastrar(Role role) {
        return roleRepository.save(role);
    }
    public void excluir(String role) {
        roleRepository.deleteById(role);
    }
}

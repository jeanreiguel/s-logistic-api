package br.com.senai.api.assembler;


import br.com.senai.api.model.RoleDTO;
import br.com.senai.api.input.RoleInputDTO;
import br.com.senai.domain.model.Role;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class RoleAssembler {

    private ModelMapper modelMapper;

    public RoleDTO toDTO(Role role) {
        return modelMapper.map(role, RoleDTO.class);
    }

    public Role toEntity(RoleInputDTO roleInput) {
        return modelMapper.map(roleInput, Role.class);
    }

    public List<RoleDTO> toCollection(List<Role> roles) {
        return roles.stream().map(this::toDTO).collect(Collectors.toList());
    }
}

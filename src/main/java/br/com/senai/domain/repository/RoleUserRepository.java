package br.com.senai.domain.repository;

import br.com.senai.domain.model.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {
}

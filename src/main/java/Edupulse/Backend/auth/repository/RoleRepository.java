package Edupulse.Backend.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Edupulse.Backend.auth.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long>  {
    Optional<Role> findByName(String name);
    
}

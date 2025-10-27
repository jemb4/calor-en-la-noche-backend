package dev.jesus.calor_en_la_noche.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jesus.calor_en_la_noche.role.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
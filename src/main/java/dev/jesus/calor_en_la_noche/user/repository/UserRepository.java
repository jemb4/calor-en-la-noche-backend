package dev.jesus.calor_en_la_noche.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jesus.calor_en_la_noche.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
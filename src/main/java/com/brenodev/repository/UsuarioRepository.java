package com.brenodev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brenodev.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Long, Usuario>{

	@Query(value="SELECT u FROM Usuario u WHERE u.login = ?1")
	Usuario findUserByLogin(String login);
	
}

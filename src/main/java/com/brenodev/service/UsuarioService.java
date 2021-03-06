package com.brenodev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brenodev.model.Usuario;
import com.brenodev.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public Usuario findUserByLogin(String login) {		return usuarioRepository.findUserByLogin(login);
	}
}

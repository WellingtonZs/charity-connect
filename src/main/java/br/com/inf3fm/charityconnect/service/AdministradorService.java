package br.com.inf3fm.charityconnect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.inf3fm.charityconnect.entity.Administrador;
import br.com.inf3fm.charityconnect.repository.AdministradorRepository;

@Service
public class AdministradorService {
	
	private AdministradorRepository administradorRepository;

	public AdministradorService(AdministradorRepository administradorRepository) {
		super();
		this.administradorRepository = administradorRepository;
	}
	
	public List<Administrador> findAll() {
		List<Administrador> administradores = administradorRepository.findAll();
		return administradores;
	}

}

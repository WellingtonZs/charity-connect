package br.com.inf3fm.charityconnect.service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.inf3fm.charityconnect.entity.Administrador;
import br.com.inf3fm.charityconnect.repository.AdministradorRepository;
import jakarta.transaction.Transactional;

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
	
	public Administrador findById(long id) {
		Administrador administrador = administradorRepository.findById(id).orElseThrow();
		
		return administrador;
	}
	
	@Transactional
	public Administrador create(Administrador administrador) {
		String senha = Base64.getEncoder().encodeToString(
				administrador.getSenha().getBytes());
		administrador.setSenha(senha);
		
		administrador.setDataCadastro(LocalDateTime.now());
		administrador.setStatusAdmin("ATIVO");
		
		return administradorRepository.save(administrador);
	}

}

package br.com.inf3fm.charityconnect.service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

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
	
	public Administrador findByEmail(String email) {
		Administrador administrador = administradorRepository.findByEmail(email);
		
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
	
	@Transactional
	public Administrador signin(String email,  String senha) {
		
		Administrador administrador = administradorRepository.findByEmail(email);
		if (administrador != null) {
		if (administrador.getStatusAdmin().equals("ATIVO")) {
			byte[] decodedPass = Base64.getDecoder()
					.decode(administrador.getSenha());
			if(new String(decodedPass).equals(senha)) {
				return administrador;
				}
			}
		}
		return null;
	}
	
	@Transactional
	public Administrador inativar(long id) {
		Optional<Administrador> _administrador = administradorRepository.findById(id);
		
		if (_administrador.isPresent()) {
			Administrador administradorAtualizado = _administrador.get();
			administradorAtualizado.setStatusAdmin("INATIVO");
			
			return administradorRepository.save(administradorAtualizado);
		}
		return null;
	}
	
	@Transactional
	public Administrador reativar(long id) {
		Optional<Administrador> _administrador = administradorRepository.findById(id);
		
		if (_administrador.isPresent()) {
			Administrador administradorAtualizado = _administrador.get();
			
			String senha = Base64.getEncoder()
					.encodeToString("12345678".getBytes());
			administradorAtualizado.setSenha(senha);
			administradorAtualizado.setDataCadastro(LocalDateTime.now());
			administradorAtualizado.setStatusAdmin("ATIVO");
			
			return administradorRepository.save(administradorAtualizado);
		}
		return null;
	}
	
	@Transactional
	public Administrador alterarSenha(long id, Administrador administrador) {
		Optional<Administrador> _administrador = administradorRepository.findById(id);
		
		if (_administrador.isPresent()) {
			Administrador administradorAtualizado = _administrador.get();
			String senha = Base64.getEncoder().encodeToString(administrador.getSenha().getBytes());
			administradorAtualizado.setSenha(senha);
			
			return administradorRepository.save(administradorAtualizado);
		}
		return null;
	}

}

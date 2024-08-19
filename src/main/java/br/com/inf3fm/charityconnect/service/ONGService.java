package br.com.inf3fm.charityconnect.service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.inf3fm.charityconnect.entity.ONG;
import br.com.inf3fm.charityconnect.repository.ONGRepository;
import jakarta.transaction.Transactional;

@Service
public class ONGService {
	
	private ONGRepository ongRepository;

	public ONGService(ONGRepository ongRepository) {
		super();
		this.ongRepository = ongRepository;
	}
	
	public List<ONG> findAll() {
		List<ONG> ongs = ongRepository.findAll();
		return ongs;
	}
	
	public ONG findById(long id) {
		ONG ong = ongRepository.findById(id).orElseThrow();
		
		return ong;
	}
	
	@Transactional
	public ONG create(ONG ong) {
		String senha = Base64.getEncoder().encodeToString(
				ong.getSenha().getBytes());
		ong.setSenha(senha);
		
		ong.setDataCadastro(LocalDateTime.now());
		ong.setStatusONG("ATIVO");
		
		return ongRepository.save(ong);
	}

}

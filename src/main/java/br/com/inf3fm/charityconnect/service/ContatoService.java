package br.com.inf3fm.charityconnect.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.inf3fm.charityconnect.entity.Contato;
import br.com.inf3fm.charityconnect.repository.ContatoRepository;
import jakarta.transaction.Transactional;

@Service
public class ContatoService {
	
	private ContatoRepository contatoRepository;

	public ContatoService(ContatoRepository contatoRepository) {
		super();
		this.contatoRepository = contatoRepository;
	}
	
	public List<Contato> findAll() {
		List<Contato> contatos = contatoRepository.findAll();
		return contatos;
	}
	
	public Contato findById(long id) {
		Contato contato = contatoRepository.findById(id).orElseThrow();
		
		return contato;
	}
	
	@Transactional
	public Contato create(Contato contato) {
		
		contato.setDataContato(LocalDateTime.now());
		
		return contatoRepository.save(contato);
	}
	
	@Transactional
	public Contato update(long id) {
		
		Optional<Contato> _contato = contatoRepository.findById(id);
		
		if (_contato.isPresent()) {
			Contato contatoAtualizado = _contato.get();
			contatoAtualizado.setDataContato(LocalDateTime.now());
			
			return contatoRepository.save(contatoAtualizado);
		}
		return null;
	}


}

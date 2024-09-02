package br.com.inf3fm.charityconnect.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.inf3fm.charityconnect.entity.ReprovacaoONG;
import br.com.inf3fm.charityconnect.repository.ReprovacaoONGRepository;
import jakarta.transaction.Transactional;

@Service
public class ReprovacaoONGService {
	
	private ReprovacaoONGRepository reprovacaoongRepository;

	public ReprovacaoONGService(ReprovacaoONGRepository reprovacaoongRepository) {
		super();
		this.reprovacaoongRepository = reprovacaoongRepository;
	}
	
	public List<ReprovacaoONG> findAll() {
		List<ReprovacaoONG> reprovacoes = reprovacaoongRepository.findAll();
		return reprovacoes;
	}
	
	public ReprovacaoONG findById(long id) {
		ReprovacaoONG reprovacao = reprovacaoongRepository.findById(id).orElseThrow();
		
		return reprovacao;
	}
	
	@Transactional
	public ReprovacaoONG create(ReprovacaoONG reprovacaoong) {
		
		reprovacaoong.setDataReprovacao(LocalDateTime.now());
		reprovacaoong.setStatusReprovacao("REPROVADA");
		
		return reprovacaoongRepository.save(reprovacaoong);
	}
	
	@Transactional
	public ReprovacaoONG update(long id) {
		Optional<ReprovacaoONG> _reprovacaoong = reprovacaoongRepository.findById(id);
		
		if (_reprovacaoong.isPresent()) {
			ReprovacaoONG reprovacaoongAtualizada = _reprovacaoong.get();
			reprovacaoongAtualizada.setDataReprovacao(LocalDateTime.now());
			reprovacaoongAtualizada.setStatusReprovacao("REPROVADA");
			
			return reprovacaoongRepository.save(reprovacaoongAtualizada);
		}
		return null;
	}

}

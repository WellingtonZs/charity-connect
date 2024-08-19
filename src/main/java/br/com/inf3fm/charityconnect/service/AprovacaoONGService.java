package br.com.inf3fm.charityconnect.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.inf3fm.charityconnect.entity.AprovacaoONG;
import br.com.inf3fm.charityconnect.repository.AprovacaoONGRepository;
import jakarta.transaction.Transactional;

@Service
public class AprovacaoONGService {
	
	private AprovacaoONGRepository aprovacaoongRepository;

	public AprovacaoONGService(AprovacaoONGRepository aprovacaoongRepository) {
		super();
		this.aprovacaoongRepository = aprovacaoongRepository;
	}
	
	public List<AprovacaoONG> findAll() {
		List<AprovacaoONG> aprovacoes = aprovacaoongRepository.findAll();
		return aprovacoes;
	}
	
	public AprovacaoONG findById(long id) {
		AprovacaoONG aprovacao = aprovacaoongRepository.findById(id).orElseThrow();
		
		return aprovacao;
	}
	
	@Transactional
	public AprovacaoONG create(AprovacaoONG aprovacaoong) {
		
		aprovacaoong.setDataAprovacao(LocalDateTime.now());
		aprovacaoong.setStatusAprovacao("APROVADO");
		
		return aprovacaoongRepository.save(aprovacaoong);
	}

}

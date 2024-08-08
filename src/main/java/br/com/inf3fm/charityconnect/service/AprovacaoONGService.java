package br.com.inf3fm.charityconnect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.inf3fm.charityconnect.entity.AprovacaoONG;
import br.com.inf3fm.charityconnect.repository.AprovacaoONGRepository;

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
		AprovacaoONG aprovacoes = aprovacaoongRepository.findById(id).orElseThrow();
		
		return aprovacoes;
	}

}

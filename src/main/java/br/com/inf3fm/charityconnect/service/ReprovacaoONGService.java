package br.com.inf3fm.charityconnect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.inf3fm.charityconnect.entity.ReprovacaoONG;
import br.com.inf3fm.charityconnect.repository.ReprovacaoONGRepository;

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

}

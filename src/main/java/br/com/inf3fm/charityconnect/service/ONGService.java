package br.com.inf3fm.charityconnect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.inf3fm.charityconnect.entity.ONG;
import br.com.inf3fm.charityconnect.repository.ONGRepository;

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

}

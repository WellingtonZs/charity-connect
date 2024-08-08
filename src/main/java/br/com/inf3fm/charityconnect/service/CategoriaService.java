package br.com.inf3fm.charityconnect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.inf3fm.charityconnect.entity.Categoria;
import br.com.inf3fm.charityconnect.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	private CategoriaRepository categoriaRepository;

	public CategoriaService(CategoriaRepository categoriaRepository) {
		super();
		this.categoriaRepository = categoriaRepository;
	}
	
	public List<Categoria> findAll(){
		List<Categoria> categorias = categoriaRepository.findAll();
		return categorias;
	}
	
	public Categoria findById(long id) {
		Categoria categorias = categoriaRepository.findById(id).orElseThrow();
		
		return categorias;
	}
}

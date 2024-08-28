package br.com.inf3fm.charityconnect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.inf3fm.charityconnect.entity.Categoria;
import br.com.inf3fm.charityconnect.repository.CategoriaRepository;
import jakarta.transaction.Transactional;

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
		Categoria categoria = categoriaRepository.findById(id).orElseThrow();
		
		return categoria;
	}
	
	@Transactional
	public Categoria create(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	@Transactional
	public Categoria update(Long id) {
		Optional<Categoria> _categoria = categoriaRepository.findById(id);
		
		if(_categoria.isPresent()) {
			Categoria categoriaAtualizada = _categoria.get();
			
		return categoriaRepository.save(categoriaAtualizada);
		}
		return null;
	}
}

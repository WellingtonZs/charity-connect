package br.com.inf3fm.charityconnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.inf3fm.charityconnect.entity.Categoria;
import br.com.inf3fm.charityconnect.service.CategoriaService;

@RestController
@RequestMapping("/categoria/")
public class CategoriaController {
	
	private CategoriaService categoriaService;

	public CategoriaController(CategoriaService categoriaService) {
		super();
		this.categoriaService = categoriaService;
	}
	
	@GetMapping("findAll")
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> categorias = categoriaService.findAll();
		return new ResponseEntity<List<Categoria>>(categorias, HttpStatus.OK);
	}
	
	@GetMapping("findById/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable long id) {
		
		Categoria categorias = categoriaService.findById(id);
		
		return new ResponseEntity<Categoria>(categorias, HttpStatus.OK);
	}

}

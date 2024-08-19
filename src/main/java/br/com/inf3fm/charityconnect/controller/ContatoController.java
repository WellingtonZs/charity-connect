package br.com.inf3fm.charityconnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.inf3fm.charityconnect.entity.Contato;
import br.com.inf3fm.charityconnect.service.ContatoService;

@RestController
@RequestMapping("/contato/")
public class ContatoController {
	
	private ContatoService contatoService;

	public ContatoController(ContatoService contatoService) {
		super();
		this.contatoService = contatoService;
	}
	
	@GetMapping("findAll")
	public ResponseEntity<List<Contato>> findAll() {
		List<Contato> contatos = contatoService.findAll();
		return new ResponseEntity<List<Contato>>(contatos, HttpStatus.OK);
	}
	
	@GetMapping("findById/{id}")
	public ResponseEntity<Contato> findById(@PathVariable long id) {
		Contato contato = contatoService.findById(id);
		return new ResponseEntity<Contato>(contato, HttpStatus.OK);
	}
	
	@PostMapping("create")
	public ResponseEntity<Contato> create(@RequestBody Contato contato) {
		
		Contato _contato = contatoService.create(contato);
		
		return new ResponseEntity<Contato>(_contato, HttpStatus.OK);
	}
	
}

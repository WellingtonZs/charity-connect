package br.com.inf3fm.charityconnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.inf3fm.charityconnect.entity.ReprovacaoONG;
import br.com.inf3fm.charityconnect.service.ReprovacaoONGService;

@RestController
@RequestMapping("/reprovacaoong/")
public class ReprovacaoONGController {
	
	private ReprovacaoONGService reprovacaoongService;

	public ReprovacaoONGController(ReprovacaoONGService reprovacaoongService) {
		super();
		this.reprovacaoongService = reprovacaoongService;
	}
	
	@GetMapping("findAll")
	public ResponseEntity<List<ReprovacaoONG>> findAll() {
		List<ReprovacaoONG> reprovacoes = reprovacaoongService.findAll();
		return new ResponseEntity<List<ReprovacaoONG>>(reprovacoes, HttpStatus.OK);
	}
	
	@GetMapping("findById/{id}")
	public ResponseEntity<ReprovacaoONG> findById(@PathVariable long id) {
		
		ReprovacaoONG reprovacao = reprovacaoongService.findById(id);
		
		return new ResponseEntity<ReprovacaoONG>(reprovacao, HttpStatus.OK);
	}
	
	@PostMapping("create")
	public ResponseEntity<ReprovacaoONG> create(@RequestBody ReprovacaoONG reprovacaoong) {
		
		ReprovacaoONG _reprovacaoong = reprovacaoongService.create(reprovacaoong);
		
		return new ResponseEntity<ReprovacaoONG>(_reprovacaoong, HttpStatus.OK);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<ReprovacaoONG> update(
			@PathVariable long id) {
		
		ReprovacaoONG _reprovacaoong = reprovacaoongService.update(id);
		
		return new ResponseEntity<ReprovacaoONG>(
				_reprovacaoong, HttpStatus.OK);
	}

}

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

import br.com.inf3fm.charityconnect.entity.AprovacaoONG;
import br.com.inf3fm.charityconnect.service.AprovacaoONGService;

@RestController
@RequestMapping("/aprovacaoong/")
public class AprovacaoONGController {
	
	private AprovacaoONGService aprovacaoongService;

	public AprovacaoONGController(AprovacaoONGService aprovacaoongService) {
		super();
		this.aprovacaoongService = aprovacaoongService;
	}
	
	@GetMapping("findAll")
	public ResponseEntity<List<AprovacaoONG>> findAll() {
		List<AprovacaoONG> aprovacoes = aprovacaoongService.findAll();
		return new ResponseEntity<List<AprovacaoONG>>(aprovacoes, HttpStatus.OK);
	}
	
	@GetMapping("findById/{id}")
	public ResponseEntity<AprovacaoONG> findById(@PathVariable long id) {
		
		AprovacaoONG aprovacao = aprovacaoongService.findById(id);
		
		return new ResponseEntity<AprovacaoONG>(aprovacao, HttpStatus.OK);
	}
	
	@PostMapping("create")
	public ResponseEntity<AprovacaoONG> create(@RequestBody AprovacaoONG aprovacaoong) {
		
		AprovacaoONG _aprovacaoong = aprovacaoongService.create(aprovacaoong);
		
		return new ResponseEntity<AprovacaoONG>(_aprovacaoong, HttpStatus.OK);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<AprovacaoONG> update(
			@PathVariable long id) {
		
		AprovacaoONG _aprovacaoong = aprovacaoongService.update(id);
		
		return new ResponseEntity<AprovacaoONG>(
				_aprovacaoong, HttpStatus.OK);
	}

}

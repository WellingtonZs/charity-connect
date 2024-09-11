package br.com.inf3fm.charityconnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.inf3fm.charityconnect.entity.ONG;
import br.com.inf3fm.charityconnect.exception.ResourceNotFoundException;
import br.com.inf3fm.charityconnect.service.ONGService;

@RestController
@RequestMapping("/ong/")
public class ONGController {
	
	private ONGService ongService;

	public ONGController(ONGService ongService) {
		super();
		this.ongService = ongService;
	}
	
	@GetMapping("findAll")
	public ResponseEntity<List<ONG>> findAll() {
		List<ONG> ongs = ongService.findAll();
			return new ResponseEntity<List<ONG>>(ongs, HttpStatus.OK);
		}
	
	@GetMapping("findById/{id}")
	public ResponseEntity<ONG> findById(@PathVariable long id) {
		
		ONG ong = ongService.findById(id);
		
		return new ResponseEntity<ONG>(ong, HttpStatus.OK);
	}
	
	@GetMapping("FindByEmail")
	public ResponseEntity<ONG> findByEmail(@RequestParam String email) {
		
		ONG ong = ongService.findByEmail(email);
		
		return new ResponseEntity<ONG>(ong, HttpStatus.OK);
	}
	
	@PostMapping("create")
	public ResponseEntity<ONG> create(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@ModelAttribute("ong") ONG ong) {
		
		ONG _ong = ongService.create(file, ong);
		
		return new ResponseEntity<ONG>(_ong,HttpStatus.OK);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody ONG ong) {

		ONG _ong = ongService
				.signin(ong.getEmail(), ong.getSenha());

		if (_ong == null) {
			throw new ResourceNotFoundException("*** Dados Incorretos! *** ");
		}

		return ResponseEntity.ok(_ong);
	}
	
	@PutMapping("inativar/{id}")
	public ResponseEntity<ONG> inativar(
			@PathVariable long id) {
		
		ONG _ong = ongService.inativar(id);
		
		return new ResponseEntity<ONG>(_ong, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<ONG> reativar(
			@PathVariable long id) {
		
		ONG _ong = ongService.reativar(id);
		
		return new ResponseEntity<ONG>(
				_ong, HttpStatus.OK);
	}
	
	@PutMapping("alterarSenha/{id}")
	public ResponseEntity<ONG> alterarSenha(
			@PathVariable long id,
			@RequestBody ONG ong) {
		
		ONG _ong = ongService.alterarSenha(id, ong);
		
		return new ResponseEntity<ONG>(
				_ong, HttpStatus.OK);
	}
}


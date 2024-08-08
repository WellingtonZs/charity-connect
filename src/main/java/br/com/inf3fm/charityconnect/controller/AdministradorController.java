package br.com.inf3fm.charityconnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.inf3fm.charityconnect.entity.Administrador;
import br.com.inf3fm.charityconnect.service.AdministradorService;

@RestController
@RequestMapping("/admin/")
public class AdministradorController {
	
	private AdministradorService administradorService;

	public AdministradorController(AdministradorService administradorService) {
		super();
		this.administradorService = administradorService;
	}
	
	@GetMapping("findAll")
	public ResponseEntity<List<Administrador>> findAll() {
		List<Administrador> administradores = administradorService.findAll();
		return new ResponseEntity<List<Administrador>>(administradores, HttpStatus.OK);
	}
	
	@GetMapping("findById/{id}")
	public ResponseEntity<Administrador> findById(@PathVariable long id) {
		
		Administrador administradores = administradorService.findById(id);
		
		return new ResponseEntity<Administrador>(administradores, HttpStatus.OK);
	}

}

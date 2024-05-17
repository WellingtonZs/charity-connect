package br.com.inf3fm.charityconnect.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "AprovacaoONG")
public class AprovacaoONG {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDateTime dataAprovacao;
	private String statusAprovacao;
	
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Administrador administrador;

}

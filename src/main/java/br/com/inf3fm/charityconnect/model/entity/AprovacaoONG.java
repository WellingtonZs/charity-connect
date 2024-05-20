package br.com.inf3fm.charityconnect.model.entity;

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
	
	@ManyToOne
	@JoinColumn(name = "ong_id")
	private ONG ong;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(LocalDateTime dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}

	public String getStatusAprovacao() {
		return statusAprovacao;
	}

	public void setStatusAprovacao(String statusAprovacao) {
		this.statusAprovacao = statusAprovacao;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public ONG getOng() {
		return ong;
	}

	public void setOng(ONG ong) {
		this.ong = ong;
	}
	
	

}

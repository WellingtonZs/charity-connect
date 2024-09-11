package br.com.inf3fm.charityconnect.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.inf3fm.charityconnect.entity.ONG;
import br.com.inf3fm.charityconnect.repository.ONGRepository;
import jakarta.transaction.Transactional;

@Service
public class ONGService {

	private ONGRepository ongRepository;

	public ONGService(ONGRepository ongRepository) {
		super();
		this.ongRepository = ongRepository;
	}

	public List<ONG> findAll() {
		List<ONG> ongs = ongRepository.findAll();
		return ongs;
	}

	public ONG findById(long id) {
		ONG ong = ongRepository.findById(id).orElseThrow();

		return ong;
	}

	public ONG findByEmail(String email) {
		ONG ong = ongRepository.findByEmail(email);

		return ong;
	}

	@Transactional
	public ONG create(ONG ong) {
		String senha = Base64.getEncoder().encodeToString(ong.getSenha().getBytes());
		ong.setSenha(senha);

		ong.setDataCadastro(LocalDateTime.now());
		ong.setFoto(null);
		ong.setStatusONG("ATIVO");

		return ongRepository.save(ong);
	}

	@Transactional
	public ONG signin(String email, String senha) {

		ONG ong = ongRepository.findByEmail(email);
		if (ong != null) {
			if (ong.getStatusONG().equals("ATIVO")) {
				byte[] decodedPass = Base64.getDecoder().decode(ong.getSenha());
				if (new String(decodedPass).equals(senha)) {
					return ong;
				}
			}
		}
		return null;
	}

	@Transactional
	public ONG inativar(long id) {
		Optional<ONG> _ong = ongRepository.findById(id);

		if (_ong.isPresent()) {
			ONG ongAtualizada = _ong.get();
			ongAtualizada.setStatusONG("INATIVO");

			return ongRepository.save(ongAtualizada);
		}
		return null;
	}

	@Transactional
	public ONG reativar(long id) {
		Optional<ONG> _ong = ongRepository.findById(id);

		if (_ong.isPresent()) {
			ONG ongAtualizada = _ong.get();

			String senha = Base64.getEncoder().encodeToString("12345678".getBytes());
			ongAtualizada.setSenha(senha);
			ongAtualizada.setDataCadastro(LocalDateTime.now());
			ongAtualizada.setStatusONG("ATIVO");

			return ongRepository.save(ongAtualizada);
		}
		return null;
	}

	@Transactional
	public ONG alterarSenha(long id, ONG ong) {
		Optional<ONG> _ong = ongRepository.findById(id);

		if (_ong.isPresent()) {
			ONG ongAtualizada = _ong.get();
			String senha = Base64.getEncoder().encodeToString(ong.getSenha().getBytes());
			ongAtualizada.setSenha(senha);

			return ongRepository.save(ongAtualizada);
		}
		return null;
	}

	public ONG create(MultipartFile file, ONG ong) {

		if (file != null && file.getSize() > 0) {
			try {
				ong.setFoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			ong.setFoto(null);
		}

		String senha = Base64.getEncoder().encodeToString(ong.getSenha().getBytes());
		ong.setSenha(senha);

		ong.setDataCadastro(LocalDateTime.now());
		ong.setStatusONG("ATIVO");

		return ongRepository.save(ong);
	}

}

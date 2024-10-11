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

	public List<ONG> findAllAprovadas() {
		List<ONG> ongs = ongRepository.findByStatusONG("Aprovada");
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
		ong.setStatusONG("Pendente");

		return ongRepository.save(ong);
	}

	@Transactional
	public ONG signin(String email, String senha) {

		ONG ong = ongRepository.findByEmail(email);
		if (ong != null) {
			if (ong.getStatusONG().equals("Aprovada")) {
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
			ongAtualizada.setStatusONG("Banida");

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
			ongAtualizada.setStatusONG("Aprovada");

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
	
	@Transactional
	public ONG alterarStatus(long id, ONG ong) {
		Optional<ONG> _ong = ongRepository.findById(id);

		if (_ong.isPresent()) {
			ONG ongAtualizada = _ong.get();
			ongAtualizada.setStatusONG(ong.getStatusONG());
			return ongRepository.save(ongAtualizada);
		}
		return null;
	}
	
	@Transactional
	public ONG update(long id, ONG ong) {
		//Verifica se o Usuario existe no banco de dados
		Optional<ONG> _ong = ongRepository.findById(id);
		
		if(_ong.isPresent()) {
			ONG ongAtualizada = _ong.get();
			
			// Atualiza os campos necessários, garantindo que não sejam nulos ou indefinidos
			
			if(ong.getNome() != null && !ong.getNome().isEmpty()) {
				ongAtualizada.setNome(ong.getNome());
			}
			
			if(ong.getNomeRep() != null && !ong.getNomeRep().isEmpty()) {
				ongAtualizada.setNomeRep(ong.getNomeRep());
			}
			
			if(ong.getEmail() != null && !ong.getEmail().isEmpty()) {
				ongAtualizada.setEmail(ong.getEmail());
			}
			
			if(ong.getTelefone() != null && !ong.getTelefone().isEmpty()) {
				ongAtualizada.setTelefone(ong.getTelefone());
			}
			
			if(ong.getDescAtuacao() != null && !ong.getDescAtuacao().isEmpty()) {
				ongAtualizada.setDescAtuacao(ong.getDescAtuacao());
			}
			
			if(ong.getInteresse() != null && !ong.getInteresse().isEmpty()) {
				ongAtualizada.setInteresse(ong.getInteresse());
			}
			
			if(ong.getCep() != null && !ong.getCep().isEmpty()) {
				ongAtualizada.setCep(ong.getCep());
			}
			
			if(ong.getCidade() != null && !ong.getCidade().isEmpty()) {
				ongAtualizada.setCidade(ong.getCidade());
			}
			
			if(ong.getCnpj() != null && !ong.getCnpj().isEmpty()) {
				ongAtualizada.setCnpj(ong.getCnpj());
			}
			
			if(ong.getUf() != null && !ong.getUf().isEmpty()) {
				ongAtualizada.setUf(ong.getUf());
			}
			
			if(ong.getEndereco() != null && !ong.getEndereco().isEmpty()) {
				ongAtualizada.setEndereco(ong.getEndereco());
			}
			
			if(ong.getBairro() != null && !ong.getBairro().isEmpty()) {
				ongAtualizada.setBairro(ong.getBairro());
			}
			
			// Atualiza o usuário no banco de dados
			return ongRepository.save(ongAtualizada);
		}
		
		//Se o usuário não for encontrado retorna null ou lança uma exceção
		return null;
		
	}
	
	@Transactional
	public ONG updateFoto(MultipartFile file, long id, ONG ong) {
		Optional<ONG> _ong = ongRepository.findById(id);
		
		if (_ong.isPresent()) {
			
			ONG ongAtualizada = _ong.get();
			
			if (file != null && file.getSize() > 0) {
				try {
					ongAtualizada.setFoto(file.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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
		ong.setStatusONG("Pendente");

		return ongRepository.save(ong);
	}

}
package br.com.inf3fm.charityconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.inf3fm.charityconnect.entity.AprovacaoONG;

@Repository
public interface AprovacaoONGRepository extends JpaRepository<AprovacaoONG, Long> {
	
	List<AprovacaoONG> findAll();

}

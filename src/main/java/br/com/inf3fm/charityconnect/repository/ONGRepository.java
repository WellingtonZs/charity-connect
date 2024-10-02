package br.com.inf3fm.charityconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.inf3fm.charityconnect.entity.ONG;

@Repository
public interface ONGRepository extends JpaRepository<ONG, Long> {
	
	List<ONG> findAll();
	
	ONG findByEmail(String email);
	
	List<ONG> findByStatusONGNot(String statusONG);
	
	List<ONG> findByStatusONG(String statusONG);

}

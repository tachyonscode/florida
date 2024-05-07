package br.com.pernambucanas.florida.fileupload.cpfconvert;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CpfRealToFakeRepository extends JpaRepository<CpfRealToFake, Integer> {
	
	@Query(value = "select cr.* from account_converted ac inner join account_migrado am ON am.account_id_ext = ac.id_De inner join cpf_real_to_fake cr ON CAST(cr.cpf_fake AS numeric) = CAST(am.cpf  AS numeric)", 
			nativeQuery = true)
	Collection<CpfRealToFake> findAllCpfRealToFake();

}

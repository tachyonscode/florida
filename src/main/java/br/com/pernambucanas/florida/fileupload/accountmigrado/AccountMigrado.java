package br.com.pernambucanas.florida.fileupload.accountmigrado;


import java.math.BigInteger;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AccountMigrado {

	@Id
	@GeneratedValue
	private Integer id;
	
	private BigInteger accountId;  //account_id
	
	private BigInteger accountIdExt; //account_id_ext
	
	private BigInteger accountIdPay; //account_id_pay
	
	private Integer programId; //program_id
	
	private String cpf; //cpf
	
}

package br.com.pernambucanas.florida.fileupload.controleprocessos;

import java.math.BigInteger;

import jakarta.persistence.Entity;
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
public class ControleProcessos {

	@Id
	private BigInteger idConta;
	
	private String mesCorte;
	
}

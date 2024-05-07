package br.com.pernambucanas.florida.fileupload.controleprocessos;

import java.math.BigInteger;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ControleProcessosCsvRepresentation {
	
	@CsvBindByName(column = "id_conta_dock")
	private BigInteger id;
	
	@CsvBindByName(column = "mes_corte")
	private String mesCorte;
	
}

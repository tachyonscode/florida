package br.com.pernambucanas.florida.filedownload.accountconverted;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountConvertedCsvRepresentation {

	@CsvIgnore
	private Integer id;
	@CsvBindByName(column = "id_De")
	private BigInteger idDe;
	@CsvBindByName(column = "id_Para")
	private String idPara;
	@CsvBindByName(column = "numeroContrato")
	private String numeroContrato;
	
}

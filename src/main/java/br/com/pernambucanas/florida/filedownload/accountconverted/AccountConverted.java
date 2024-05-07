package br.com.pernambucanas.florida.filedownload.accountconverted;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigInteger;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AccountConverted {

	@Id
	@GeneratedValue
	@CsvIgnore
	private Integer id;
	@CsvBindByName(column = "id_De")
	private BigInteger idDe;  
	@CsvBindByName(column = "id_Para")
	private String idPara; 
	@CsvBindByName(column = "numeroContrato")
	private String numeroContrato;
	
}

package br.com.pernambucanas.florida.fileupload.accountmigrado;


import com.opencsv.bean.CsvBindByName;
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
public class AccountMigradoCsvRepresentation {

	@CsvBindByName(column = "account_id")
	private BigInteger accountId;
	@CsvBindByName(column = "account_id_ext")
	private BigInteger accountIdExt;
	@CsvBindByName(column = "account_id_pay")
	private BigInteger accountIdPay;
	@CsvBindByName(column = "program_id")
	private Integer programId;
	@CsvBindByName(column = "cpf")
	private String cpf;
	
}

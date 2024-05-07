package br.com.pernambucanas.florida.fileupload.cpfconvert;

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
public class CpfRealToFakeCsvRepresentation {

	@CsvBindByName(column = "cpf_fake")
	private String cpfFake;
	
	@CsvBindByName(column = "cpf_real")
	private String cpfReal;
	
}

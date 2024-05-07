package br.com.pernambucanas.florida.fileupload.cpfconvert;

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

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CpfRealToFake {

	@Id
	@GeneratedValue
	@CsvIgnore
	private Integer id;

	@CsvBindByName(column = "cpf_fake")
	private String cpfFake;
	
	@CsvBindByName(column = "cpf_real")
	private String cpfReal;
	
}

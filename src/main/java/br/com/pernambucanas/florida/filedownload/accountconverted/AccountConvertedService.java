package br.com.pernambucanas.florida.filedownload.accountconverted;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import br.com.pernambucanas.florida.fileupload.accountmigrado.AccountMigrado;
import br.com.pernambucanas.florida.fileupload.accountmigrado.AccountMigradoRepository;
import br.com.pernambucanas.florida.fileupload.controleprocessos.ControleProcessos;
import br.com.pernambucanas.florida.fileupload.controleprocessos.ControleProcessosRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountConvertedService {

	private final AccountConvertedRepository repositoryAccountConverted;
	
	private final AccountMigradoRepository repositoryAccountMigrado;
	
	private final ControleProcessosRepository repositoryControleProcessos;	
	
	
	public StringWriter  downloadAccountConverted(Integer lote) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		
		convertLoteAccountMigrado(lote);	
		List<AccountConverted> listaAccountConverted = repositoryAccountConverted.findAll();
		
		return parseBeanToCsv(lote, listaAccountConverted);
	}
	
	private void convertLoteAccountMigrado(Integer lote) {
		
		List<AccountMigrado> listaAccountMigrado = repositoryAccountMigrado.findAll();
		Iterator<AccountMigrado> iterator = listaAccountMigrado.iterator();
		 
		while (iterator.hasNext()) {
			 
			AccountMigrado data = iterator.next();
			Optional<ControleProcessos> controleProcessos = repositoryControleProcessos.findById(data.getAccountIdExt());
			
			controleProcessos.ifPresent((bean) -> {
			    
				AccountConverted convertido = new AccountConverted();
				convertido.setIdDe(data.getAccountIdExt());
				convertido.setIdPara(data.getAccountId() + "f" + FaturaMes.valueOf(bean.getMesCorte()).getValor());
				convertido.setNumeroContrato(7 + String.format("%010d", data.getAccountId()));
				repositoryAccountConverted.save(convertido);
			});
			
		}
		
	}
	
	private static StringWriter  parseBeanToCsv(Integer lote, List<AccountConverted> listaAccountConverted) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		
        StringWriter stringWriter = new StringWriter();

        MappingStrategy columnStrategy = new HeaderColumnNameMappingStrategy();
        columnStrategy.setType(AccountConverted.class);

        //bean to csv writer
        StatefulBeanToCsv csvBuilder =new StatefulBeanToCsvBuilder(stringWriter)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withMappingStrategy(columnStrategy)
                .build();

        csvBuilder.write(listaAccountConverted);
        return stringWriter;
		
	}
}

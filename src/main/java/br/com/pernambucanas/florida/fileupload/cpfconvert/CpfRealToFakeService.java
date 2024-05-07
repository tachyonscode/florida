package br.com.pernambucanas.florida.fileupload.cpfconvert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CpfRealToFakeService {

	private final CpfRealToFakeRepository repository;

    public Integer uploadCpfRealToFake(MultipartFile file) throws IOException {
        Set<CpfRealToFake> cpfRealToFake = parseCsv(file);
        repository.saveAll(cpfRealToFake);
        return cpfRealToFake.size();
    }

    private Set<CpfRealToFake> parseCsv(MultipartFile file) throws IOException {
        try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<CpfRealToFakeCsvRepresentation> strategy =
                    new HeaderColumnNameMappingStrategy<>();
            strategy.setType(CpfRealToFakeCsvRepresentation.class);
            CsvToBean<CpfRealToFakeCsvRepresentation> csvToBean =
                    new CsvToBeanBuilder<CpfRealToFakeCsvRepresentation>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> CpfRealToFake.builder()
                            .cpfFake(csvLine.getCpfFake())
                            .cpfReal(csvLine.getCpfReal())
                            .build()
                    )
                    .collect(Collectors.toSet());
        }
    }
    
    public StringWriter  downloadCpfRealToFake(Integer lote) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		
    	Collection<CpfRealToFake> listaCpfRealToFake = repository.findAllCpfRealToFake();
		
		return parseBeanToCsv(lote, List.copyOf(listaCpfRealToFake));
	}
    
    private static StringWriter  parseBeanToCsv(Integer lote, List<CpfRealToFake> listaCpfRealToFake) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		
        StringWriter stringWriter = new StringWriter();

        MappingStrategy columnStrategy = new HeaderColumnNameMappingStrategy();
        columnStrategy.setType(CpfRealToFake.class);

        //bean to csv writer
        StatefulBeanToCsv csvBuilder =new StatefulBeanToCsvBuilder(stringWriter)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withMappingStrategy(columnStrategy)
                .build();

        csvBuilder.write(listaCpfRealToFake);
        return stringWriter;
		
	}

	
}

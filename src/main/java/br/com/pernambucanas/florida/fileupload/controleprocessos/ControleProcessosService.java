package br.com.pernambucanas.florida.fileupload.controleprocessos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ControleProcessosService {

	private final ControleProcessosRepository repository;

    public Integer uploadControleProcessos(MultipartFile file) throws IOException {
        
        return parseCsv(file);
    }

    private Integer parseCsv(MultipartFile file) throws IOException {
    	
        try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<ControleProcessosCsvRepresentation> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(ControleProcessosCsvRepresentation.class);
            CsvToBean<ControleProcessosCsvRepresentation> csvToBean = new CsvToBeanBuilder<ControleProcessosCsvRepresentation>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .withSeparator(';')
                            .build();
           
            
            Integer contador = 1;
            Integer contaGeral = 1;
            List<ControleProcessos> lista = new ArrayList<ControleProcessos>(); 
            
            Iterator<ControleProcessosCsvRepresentation> iterator = csvToBean.iterator();
            
            while (iterator.hasNext()) {
            	if(contador == 100000) {
            		repository.saveAll(lista);
            		lista = new ArrayList<ControleProcessos>(); 
            		contador = 1;
            	}
            	
            	ControleProcessosCsvRepresentation csvData = iterator.next();
            	ControleProcessos mesCorte = new ControleProcessos();
            	mesCorte.setIdConta(csvData.getId());
            	mesCorte.setMesCorte(csvData.getMesCorte());
            	lista.add(mesCorte);
            	contador++;
            	contaGeral++;
            }
            repository.saveAll(lista);
            return contaGeral;
            
        }
    }
}

package br.com.pernambucanas.florida.fileupload.accountmigrado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountMigradoService {
	
	private final AccountMigradoRepository repository;

    public Integer uploadAccountMigrado(MultipartFile file) throws IOException {
        Set<AccountMigrado> accountMigrado = parseCsv(file);
        repository.saveAll(accountMigrado);
        return accountMigrado.size();
    }

    private Set<AccountMigrado> parseCsv(MultipartFile file) throws IOException {
        try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<AccountMigradoCsvRepresentation> strategy =
                    new HeaderColumnNameMappingStrategy<>();
            strategy.setType(AccountMigradoCsvRepresentation.class);
            CsvToBean<AccountMigradoCsvRepresentation> csvToBean =
                    new CsvToBeanBuilder<AccountMigradoCsvRepresentation>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> AccountMigrado.builder()
                            .accountId(csvLine.getAccountId())
                            .accountIdExt(csvLine.getAccountIdExt())
                            .accountIdPay(csvLine.getAccountIdPay())
                            .programId(csvLine.getProgramId())
                            .cpf(csvLine.getCpf())
                            .build()
                    )
                    .collect(Collectors.toSet());
        }
    }
}

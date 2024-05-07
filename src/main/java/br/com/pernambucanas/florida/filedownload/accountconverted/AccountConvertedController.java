package br.com.pernambucanas.florida.filedownload.accountconverted;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.StringWriter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/florida/accountconverted")
@RequiredArgsConstructor
public class AccountConvertedController {

	private final AccountConvertedService service;
	
	@GetMapping(value = "/download")
	public ResponseEntity<String> uploadAccountMigrado(@RequestParam("idLote") Integer lote ) throws IOException {

		try {
			
			StringWriter stringWriter = service.downloadAccountConverted(lote);
				
			return ResponseEntity.ok()
			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"TiveaConverteIds.csv\"")
			.body(stringWriter.toString());
		
		} catch (Exception e){
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
	}

	
	    
	
}

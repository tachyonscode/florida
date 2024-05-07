package br.com.pernambucanas.florida.fileupload.accountmigrado;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/florida/accountmigrado")
@RequiredArgsConstructor
public class AccountMigradoController {

	private final AccountMigradoService service;
	
	@PostMapping(value = "/upload", consumes = {"multipart/form-data"})
	public ResponseEntity<Integer> uploadAccountMigrado(@RequestPart("file")MultipartFile file) throws IOException {
	    return ResponseEntity.ok(service.uploadAccountMigrado(file));
	}
	    
}

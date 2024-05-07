package br.com.pernambucanas.florida.fileupload.controleprocessos;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/florida/controleprocessos")
@RequiredArgsConstructor
public class ControleProcessosController {
	
	private final ControleProcessosService service;
	
	@PostMapping(value = "/upload", consumes = {"multipart/form-data"})
	public ResponseEntity<Integer> uploadControleProcessos(@RequestPart("file") MultipartFile file) throws IOException {
	    return ResponseEntity.ok(service.uploadControleProcessos(file));
	}
	    

}

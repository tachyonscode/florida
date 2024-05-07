package br.com.pernambucanas.florida.fileupload.cpfconvert;

import java.io.IOException;
import java.io.StringWriter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/florida/cpfconvert")
@RequiredArgsConstructor
public class CpfRealToFakeController {
	
	private final CpfRealToFakeService service;
	
	@PostMapping(value = "/upload", consumes = {"multipart/form-data"})
	public ResponseEntity<Integer> uploadCpfRealToFake(@RequestPart("file")MultipartFile file) throws IOException {
	    return ResponseEntity.ok(service.uploadCpfRealToFake(file));
	}
	
	@GetMapping(value = "/download")
	public ResponseEntity<String> downloadCpfRealToFake(@RequestParam("idLote") Integer lote ) throws IOException {

		try {
			
			StringWriter stringWriter = service.downloadCpfRealToFake(lote);
				
			return ResponseEntity.ok()
			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"CpfRealtoFake.csv\"")
			.body(stringWriter.toString());
		
		} catch (Exception e){
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
	}

}

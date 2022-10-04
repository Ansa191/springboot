package com.excel.mysql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.excel.mysql.helper.ExcelHelper;
import com.excel.mysql.model.Audience;
import com.excel.mysql.service.AudienceService;

@RestController
@CrossOrigin("*")
public class Controller {
	@Autowired
	AudienceService audienceService;
	
	@PostMapping("/upload/file")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		if(ExcelHelper.checkExcelFormat(file))
		{
			audienceService.save(file);
			return new ResponseEntity<>("SuccessFully Uploaded Data!",HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("Invalid File Format",HttpStatus.BAD_REQUEST);
		
	}
	@GetMapping("/file")
	public ResponseEntity<List<Audience>> getAll(){
		return new ResponseEntity<List<Audience>>(audienceService.getAllAudience(), HttpStatus.ACCEPTED);
	}

}

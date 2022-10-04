package com.excel.mysql.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excel.mysql.helper.ExcelHelper;
import com.excel.mysql.model.Audience;
import com.excel.mysql.repo.AudienceRepo;

@Service
public class AudienceService {
	@Autowired
	AudienceRepo audienceRepo;

	public void save(MultipartFile file) {
		try {
			List<Audience> audiences = ExcelHelper.convertExceltoAudiences(file.getInputStream());
			audienceRepo.saveAll(audiences);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Audience> getAllAudience() {
		return audienceRepo.findAll();
	}
}

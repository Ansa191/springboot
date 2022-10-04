package com.examserver.servicesImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examserver.models.exam.Category;
import com.examserver.repositries.CategoryRepo;
import com.examserver.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
 @Autowired
 private CategoryRepo categoryRepo;
	@Override
	public Category createCategory(Category category) {
		return this.categoryRepo.save(category);
	}

	@Override
	public Category getCategory(Long cid) {
		return this.categoryRepo.findById(cid).get();
	}

	@Override
	public void deleteCategory(Long cid) {
		this.categoryRepo.deleteById(cid);

	}

	@Override
	public Category updateCategory(Category cat) {
		return this.categoryRepo.save(cat);
	}

	@Override
	public Set<Category> getCategories() {
		return new HashSet<>(this.categoryRepo.findAll());
	}

}

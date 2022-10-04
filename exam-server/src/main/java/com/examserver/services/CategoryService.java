package com.examserver.services;

import java.util.Set;

import com.examserver.models.exam.Category;

public interface CategoryService {
	//create
	Category createCategory(Category category);
	//get
	Category getCategory(Long cid);
	//delete
	void deleteCategory(Long cid);
	//update
	Category updateCategory(Category cat);
	//get all
	Set<Category> getCategories();
	
}

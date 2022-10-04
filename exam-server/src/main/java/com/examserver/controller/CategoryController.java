package com.examserver.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examserver.models.exam.Category;
import com.examserver.services.CategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
   @Autowired
   private CategoryService categoryService;
   
   @PostMapping("/")
   public Category createCategory(@RequestBody Category category){
	   Category category2= this.categoryService.createCategory(category);
	   return category2;
   }
   @GetMapping("/{catId}")
   public ResponseEntity<Category> getCategory(@PathVariable Long catId){
	   Category category= categoryService.getCategory(catId);
	   return ResponseEntity.ok(category);
	   }
	
   @GetMapping("/")
   public ResponseEntity<?> getCategories(){
	   Set<Category> category= categoryService.getCategories();
	   return ResponseEntity.ok(category);
	   }
   @DeleteMapping("/{catId}")
   public ResponseEntity<?> deleteCategory(@PathVariable Long catId){
	   this.categoryService.deleteCategory(catId);
	   return ResponseEntity.ok("Deleted");
   }
   @PutMapping("/")
   public ResponseEntity<?> updateCategory(@RequestBody Category category){
	  Category cat= this.categoryService.updateCategory(category);
	  return ResponseEntity.ok(cat);
   }
}

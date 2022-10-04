package com.examserver.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examserver.models.exam.Quiz;
import com.examserver.services.QuizService;


@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
 @Autowired
 private QuizService quizService;
 
 
 @PostMapping("/")
 public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz q){
	   Quiz quiz= quizService.createQuiz(q);
	   return new ResponseEntity<Quiz>(quiz,HttpStatus.CREATED);
 }
 @GetMapping("/{qId}")
 public ResponseEntity<Quiz> getQuiz(@PathVariable Long qId){
	   Quiz quiz= quizService.getQuiz(qId);
	   return ResponseEntity.ok(quiz);
	   }
	
 @GetMapping("/")
 public ResponseEntity<?> getCategories(){
	   Set<Quiz> quiz= (quizService.getQuizs());
	   return ResponseEntity.ok(quiz);
	   }
 @DeleteMapping("/{qId}")
 public ResponseEntity<String> deleteQuiz(@PathVariable Long qId){
	   this.quizService.deleteQuiz(qId);
	   return new ResponseEntity<String>(HttpStatus.ACCEPTED);
 }
 @PutMapping("/")
 public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz){
	  Quiz cat= this.quizService.updateQuiz(quiz);
	  return ResponseEntity.ok(cat);
 }
 @GetMapping("/category/{catId}")
 public ResponseEntity<List<Quiz>> getQuizbyCategory(@PathVariable Long catId) {
	 List<Quiz> quizzes= quizService.getQuizbyCategory(catId);
	 return new ResponseEntity<List<Quiz>>(quizzes,HttpStatus.ACCEPTED);
 }
 //find by active
 @GetMapping("/active")
 public ResponseEntity<List<Quiz>> getbyActive() {
	 List<Quiz> quizzes= quizService.getActiveQuizs();
	 return new ResponseEntity<List<Quiz>>(quizzes,HttpStatus.ACCEPTED);
 }
 
 //find by active and category
 
 @GetMapping("/acategory/{catId}")
 public ResponseEntity<List<Quiz>> getActiveQuizbyCategory(@PathVariable Long catId) {
	 List<Quiz> quizzes= quizService.getActiveQuizByCategory(catId);
	 System.out.println(quizzes);
	 return new ResponseEntity<List<Quiz>>(quizzes,HttpStatus.ACCEPTED);
 }
}

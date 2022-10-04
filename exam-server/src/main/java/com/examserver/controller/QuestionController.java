package com.examserver.controller;

import java.util.List;
import java.util.Map;
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

import com.examserver.models.exam.Question;
import com.examserver.services.QuestionService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
@Autowired
private QuestionService questionService;
	  @PostMapping("/")
	   public ResponseEntity<Question> createQuestion(@RequestBody Question q){
		   Question question= questionService.createQuestion(q);
		   return new ResponseEntity<Question>(question,HttpStatus.ACCEPTED);
	   }
	   @GetMapping("/{qId")
	   public ResponseEntity<Question> getQuestion(@PathVariable Long qId){
		   Question question= questionService.getQuestion(qId);
		   return ResponseEntity.ok(question);
		   }
		
	   @GetMapping("/")
	   public ResponseEntity<?> getCategories(){
		   Set<Question> question= questionService.getQuestions();
		   return ResponseEntity.ok(question);
		   }
	   @DeleteMapping("/{qId}")
	   public ResponseEntity deleteQuestion(@PathVariable Long qId){
		   this.questionService.deleteQuestion(qId);
		   return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	   }
	   @PutMapping("/")
	   public ResponseEntity<?> updateQuestion(@RequestBody Question question){
		  Question cat= this.questionService.updateQuestion(question);
		  return ResponseEntity.ok(cat);
	   }
	   @GetMapping("/quiz/{qId}")
	   public ResponseEntity<?> getQuestionsOfQuiz (@PathVariable Long qId) {
		  List<Question> questions= questionService.getQuestionsOfQuiz(qId);
		  for (Question question : questions) {
			  question.setAnswer("");
			
		}
	     return ResponseEntity.ok(questions);
	   }
	   @GetMapping("/{qId}")
	   public ResponseEntity<?> getAllQuestionsOfQuiz (@PathVariable Long qId) {
		  List<Question> questions= questionService.getAllQuestionsofQuiz(qId);
	     return new ResponseEntity<>(questions,HttpStatus.ACCEPTED);
	   }
	   @PostMapping("/eval-quiz")
	   public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
		   Integer correct=0;
		   Integer attempt=0;
		   for (Question question : questions) {
			    if(question.getAnswer().equals(question.getGivenAnswer()))
			    {
			    	correct++;
			    }
			    if(question.getAnswer() != null || question.getAnswer().trim() != "")
			    {
			    	attempt++;
			    }
			    
		}
		  
		  Integer totalMarks= Integer.parseInt(questions.get(0).getQuiz().getMaxMarks());
		  Integer singleQuestionMarks= totalMarks /  Integer.parseInt(questions.get(0).getQuiz().getNoOfQuestions());
		  Integer obtainMarks= singleQuestionMarks * correct;
		  
		  Map<String, Object> map= Map.of("totalMarks", totalMarks , "obtainMarks",obtainMarks,"correct",correct,"attempt",attempt);
		  
		  return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
	   }
	
}

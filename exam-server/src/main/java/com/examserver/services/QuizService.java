package com.examserver.services;

import java.util.List;
import java.util.Set;

import com.examserver.models.exam.Category;
import com.examserver.models.exam.Quiz;

public interface QuizService {
  //create
	Quiz createQuiz(Quiz q);
	//delete
	void deleteQuiz(Long qid);
	//get 1
	Quiz getQuiz(Long qid);
	//get all
	Set<Quiz> getQuizs();
	//update
	Quiz updateQuiz(Quiz q);
	
	//get quiz by category
	List<Quiz> getQuizbyCategory(Long id);
	
	//get active quizzes only
	List<Quiz> getActiveQuizs();
	
	//get active quiz by category
	List<Quiz> getActiveQuizByCategory(Long catId);
}

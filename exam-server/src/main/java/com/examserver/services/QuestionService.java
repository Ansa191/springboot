package com.examserver.services;

import java.util.List;
import java.util.Set;

import com.examserver.models.exam.Question;

public interface QuestionService {
     //add
	Question createQuestion(Question q);
    //get all
	Set<Question> getQuestions();
	//get 1
	Question getQuestion(Long qid);
	//delete
	void deleteQuestion(Long qid);
	//update
	Question updateQuestion(Question q);
	//get questions of quiz
	List<Question> getQuestionsOfQuiz(Long quizId);
	
	//getall question of quiz
	List<Question> getAllQuestionsofQuiz(Long quizId);
	
}

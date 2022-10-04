package com.examserver.servicesImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examserver.models.exam.Category;
import com.examserver.models.exam.Quiz;
import com.examserver.repositries.CategoryRepo;
import com.examserver.repositries.QuizRepo;
import com.examserver.services.QuizService;

@Service
public class QuizServiceImpl implements QuizService {
 @Autowired
 private QuizRepo quizRepo;
 @Autowired 
 private CategoryRepo categoryRepo;
 
	@Override
	public Quiz createQuiz(Quiz q) {
		return quizRepo.save(q);
	}

	@Override
	public Quiz getQuiz(Long qid) {
		return this.quizRepo.findById(qid).get();
	}

	@Override
	public Set<Quiz> getQuizs() {
		return new HashSet<>(this.quizRepo.findAll());
	}

	@Override
	public Quiz updateQuiz(Quiz q) {
		return this.quizRepo.save(q);
	}

	@Override
	public void deleteQuiz(Long qid) {
		quizRepo.deleteById(qid);
		
	}

	@Override
	public List<Quiz> getQuizbyCategory(Long id) {
	Category cat=categoryRepo.findById(id).get();
	List<Quiz> quizs= quizRepo.findAllByCategory(cat);
		return quizs;
	}

	@Override
	public List<Quiz> getActiveQuizs() {
		return quizRepo.findByActive(true);
	}

	@Override
	public List<Quiz> getActiveQuizByCategory(Long catId) {
		Category c= categoryRepo.findById(catId).get();	
		return quizRepo.findByCategoryAndActive(c, true);
	}

}

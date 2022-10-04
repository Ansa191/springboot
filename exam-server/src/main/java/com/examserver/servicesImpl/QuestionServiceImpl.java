package com.examserver.servicesImpl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examserver.models.exam.Question;
import com.examserver.models.exam.Quiz;
import com.examserver.repositries.QuestionRepo;
import com.examserver.repositries.QuizRepo;
import com.examserver.services.QuestionService;
@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepo questionRepo;
	@Autowired
	private QuizRepo quizRepo;
	
	@Override
	public Question createQuestion(Question q) {
		return this.questionRepo.save(q);
	}

	@Override
	public Set<Question> getQuestions() {
		return new HashSet<>(this.questionRepo.findAll());
	}

	@Override
	public Question getQuestion(Long qid) {
		return this.questionRepo.findById(qid).get();
	}

	@Override
	public void deleteQuestion(Long qid) {
		this.questionRepo.deleteById(qid);

	}

	@Override
	public Question updateQuestion(Question q) {
		return this.questionRepo.save(q);
	}

	@Override
	public List<Question> getQuestionsOfQuiz(Long quizId) {
		Quiz quiz= quizRepo.findById(quizId).get();
		List<Question> questions= questionRepo.findAllByQuiz(quiz);
		if(questions.size()> Integer.parseInt(quiz.getNoOfQuestions()))
		{
			questions= questions.subList(0, Integer.parseInt(quiz.getNoOfQuestions()+1));
		}
		Collections.shuffle(questions);
		return questions;
	}

	@Override
	public List<Question> getAllQuestionsofQuiz(Long quizId) {
		Quiz quiz= quizRepo.findById(quizId).get();
		return questionRepo.findAllByQuiz(quiz);
   }

	

}

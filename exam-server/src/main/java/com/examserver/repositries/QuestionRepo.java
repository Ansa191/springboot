package com.examserver.repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examserver.models.exam.Question;
import com.examserver.models.exam.Quiz;

public interface QuestionRepo extends JpaRepository<Question, Long> {
  List<Question> findAllByQuiz(Quiz q);
}

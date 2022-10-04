package com.examserver.repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examserver.models.exam.Category;
import com.examserver.models.exam.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Long> {
	public List<Quiz> findAllByCategory(Category category);
	public List<Quiz> findByActive(boolean a);
	public List<Quiz> findByCategoryAndActive(Category category,boolean a);
}

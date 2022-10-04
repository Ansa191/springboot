package com.examserver.repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examserver.models.exam.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

}

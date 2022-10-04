package com.excel.mysql.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excel.mysql.model.Audience;

public interface AudienceRepo extends JpaRepository<Audience, Integer> {

}

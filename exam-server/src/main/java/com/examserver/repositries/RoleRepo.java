package com.examserver.repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examserver.models.Roles;

public interface RoleRepo extends JpaRepository<Roles, Long> {

}

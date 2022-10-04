package com.examserver.repositries;
import org.springframework.data.jpa.repository.JpaRepository;

import com.examserver.models.User;

public interface UserRepo extends JpaRepository<User, Long> {
  public User findByuserName(String userName);
   
}

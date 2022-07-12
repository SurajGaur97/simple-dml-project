package com.simpledmlproject.simpledmlproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.simpledmlproject.simpledmlproject.entity.User;

public interface UserDao extends JpaRepository<User, Long> {
	
	@SuppressWarnings("unchecked")
	User save(User user);
	
}

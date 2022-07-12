package com.simpledmlproject.simpledmlproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.simpledmlproject.simpledmlproject.entity.Course;

public interface CourseDao extends JpaRepository<Course, Long> {
	
	@Query(value = "SELECT * FROM course", nativeQuery = true)
	List<Course> findAllActiveUsers();	//Testing 1
	
	@Query(value = "select count(id) from course", nativeQuery = true)
	int getCountOfData();	//Testing 2
}

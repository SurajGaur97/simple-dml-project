package com.simpledmlproject.simpledmlproject.services;

import java.io.IOException;
import java.util.List;

import com.simpledmlproject.simpledmlproject.dto.UserDto;
import com.simpledmlproject.simpledmlproject.entity.Course;
import com.simpledmlproject.simpledmlproject.entity.Details;

public interface CourseService {
	
	public List<Course> getCourses();

	public Course getCourse(Long courseId);

	public Course addCourse(Course course);

	public Course updateCourse(Course course);

	public Course deleteCourse(Long courseId);

	public int getCount();
	
	public Details getDetails();
}

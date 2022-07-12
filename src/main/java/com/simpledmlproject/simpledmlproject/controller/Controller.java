package com.simpledmlproject.simpledmlproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simpledmlproject.simpledmlproject.dto.UserDto;
import com.simpledmlproject.simpledmlproject.entity.Course;
import com.simpledmlproject.simpledmlproject.entity.User;
import com.simpledmlproject.simpledmlproject.services.CourseService;

@RestController
@RequestMapping("/suraj/gaur")
public class Controller {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/home")
	public String home() {
		return "Suraj home page!!";
	}
	
	//get courses
	@GetMapping("/courses")
	public List<Course> getCourses() {
		return this.courseService.getCourses();
	}
	
	@GetMapping("/courses/{courseId}")
	public Course getCourse(@PathVariable Long courseId) {
		return this.courseService.getCourse(courseId);
	}
	
	@PostMapping("/courses")
	public Course addCourse(@RequestBody Course course) {
		return this.courseService.addCourse(course);
	}
	
	@PutMapping("/courses")
	public Course updateCourse(@RequestBody Course course) {
		return this.courseService.updateCourse(course);
	}
	
	@DeleteMapping("/courses/{courseId}")
	public Course deleteCourse(@PathVariable Long courseId) {
		return this.courseService.deleteCourse(courseId);
	}
	
	@GetMapping("/courses/count")
	public int getCount() {
		return this.courseService.getCount();
	}
	
	@GetMapping("/getTest")
	public void getThing(@RequestBody Course course) {
		Course c = course;
		System.out.println("Hello");
	}	
	
	
	//Method for Testing the Creation of Bean with the help of annotation (@Confiuration and @Bean). 
	//The bean created under the package named 'dto' and file is 'DtoDetails'.
	@GetMapping("/getDetails")
	public void getDetails() {
		System.out.println(this.courseService.getDetails().getName());
	}
}

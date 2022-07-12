package com.simpledmlproject.simpledmlproject.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpledmlproject.simpledmlproject.dao.CourseDao;
import com.simpledmlproject.simpledmlproject.entity.Course;
import com.simpledmlproject.simpledmlproject.entity.Details;
import com.simpledmlproject.simpledmlproject.services.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	//private List<Course> list;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	Details dtl;
	
	public CourseServiceImpl() {
//		list = new ArrayList<>();
//		list.add(new Course(123, "Java Core", "This course contains Java Basics"));
//		list.add(new Course(124, "Java Advance", "This course contains Java Advance"));
	}

	@Override
	public List<Course> getCourses() {
		List<Course> crs = this.courseDao.findAllActiveUsers();	//Testing 1
		return this.courseDao.findAll();
	}

	@Override
	public Course getCourse(Long courseId) {
		Course course = courseDao.findById(courseId).get();
//		for (Course c : list) {
//			if(c.getId() == courseId) {
//				course = c;
//				break;
//			}
//		}
		return course;
	}

	@Override
	public Course addCourse(Course course) {
		//list.add(course);
		this.courseDao.save(course);
		return course;
	}

	@Override
	public Course updateCourse(Course course) {
		// TODO Auto-generated method stub
		this.courseDao.save(course);
		return course;
	}

	@Override
	public Course deleteCourse(Long courseId) {
		// TODO Auto-generated method stub
		Course course = courseDao.findById(courseId).get();
		this.courseDao.deleteById(courseId);
		return course;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.courseDao.getCountOfData();	//Testing 2
	}
	
	@Override
	public Details getDetails() {
		return this.dtl;
	}
	
}

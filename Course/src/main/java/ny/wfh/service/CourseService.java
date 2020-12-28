package ny.wfh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ny.wfh.model.Course;
import ny.wfh.repository.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	public List<Course> getAllCourses()
	{
		List<Course> courseList = new ArrayList<Course>();
		courseRepository.findAll().forEach(courseList::add);
		return courseList;
	}
	
	public Course getCourse(String id)
	{
		//return courses.stream().filter(c-> c.getCourseId().equals(id)).findFirst().get();
		return courseRepository.findById(id).get();
	}
	
	public void addCourse(Course course)
	{
		courseRepository.save(course);
	}
	
	public void updateCourse(Course course)
	{
		courseRepository.save(course);
	}
	
	public void deleteCourse(String id)
	{
		//courses.removeIf(c->c.getCourseId().equals(id));
		courseRepository.deleteById(id);
	}
	
	
}

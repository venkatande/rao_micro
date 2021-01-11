package ny.wfh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ny.wfh.model.Course;
import ny.wfh.model.CourseList;
import ny.wfh.service.CourseService;

@RestController
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@RequestMapping("/courses")
	public List<Course> getCourses()
	{
		return courseService.getAllCourses();
	}
	
	@RequestMapping("/courses/student/{studentId}")
	public CourseList getCoursesForStudentId(@PathVariable String studentId)
	{
		CourseList courseListObj=new CourseList();
		Map<String,CourseList> courseMap= new HashMap<>();
		List<Course> courseList=new ArrayList<>();
		courseList.add(new Course("JavaBoot","Java Boot","Java Boot Desc"));
		courseList.add(new Course("JavaSpring","Java Spring","Java Spring Desc"));
		courseListObj.setCourseList(courseList);
		courseMap.put("1", courseListObj);
		courseList=new ArrayList<>();
		courseListObj=new CourseList();
		courseList.add(new Course("JavaBoot","Java Boot","Java Boot Desc"));
		courseList.add(new Course("JavaSpring","Java Spring","Java Spring Desc"));
		courseList.add(new Course("JavaMicro","Java Micro","Java Micro Desc"));
		courseListObj.setCourseList(courseList);
		courseMap.put("2", courseListObj);
		courseList=new ArrayList<>();
		courseListObj=new CourseList();
		courseList.add(new Course("JavaSpring","Java Spring","Java Spring Desc"));
		courseList.add(new Course("JavaMicro","Java Micro","Java Micro Desc"));
		courseListObj.setCourseList(courseList);
		courseMap.put("3", courseListObj);
		
		return courseMap.get(studentId);
	}
	
	@RequestMapping("/courses/{id}")
	public Course getCourse(@PathVariable String id)
	{
		return courseService.getCourse(id);
	}
	
	
	@PostMapping("/courses")
	public void addCourse(@RequestBody Course course)
	{
		courseService.addCourse(course);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/courses/{id}")
	public void updateCourse(@RequestBody Course course,@PathVariable String id)
	{
		courseService.updateCourse(course);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/courses/{id}")
	public void deleteCourse(@PathVariable String id)
	{
		courseService.deleteCourse(id);
	}
	


}

package ny.wfh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import ny.wfh.model.Course;
import ny.wfh.model.CourseList;
import ny.wfh.model.Student;
import ny.wfh.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	StudentService studentService;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/studentCourses/{studentId}")
	public List<Course> getStudentCoursesById(@PathVariable String studentId)
	{
		CourseList cList= restTemplate.getForObject("http://localhost:8082/courses/student/"+studentId, CourseList.class);
		
		//CourseList cList=webClientBuilder.build().get().uri("http://Student/courses/student/"+studentId)
				//.retrieve().bodyToMono(CourseList.class).block();
		if(cList!=null)return cList.getCourseList();
		return null;
	}
	
	@RequestMapping("/students")
	public List<Student> getAllStudents()
	{
		return studentService.getAllStudents();
	}
	
	@RequestMapping("/students/{id}")
	public Student getStudent(@PathVariable String id)
	{
		return studentService.getStudent(id);
	}
	
	@PostMapping("/students")
	public void addStudent(@RequestBody Student student)
	{
		studentService.addStudent(student);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/students/{id}")
	public void updateCourse(@RequestBody Student student,@PathVariable String id)
	{
		studentService.updateStudent(student);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/students/{id}")
	public void deleteStudent(@PathVariable String id)
	{
		studentService.deleteStudent(id);
	}
	

}
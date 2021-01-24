package ny.wfh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import ny.wfh.model.Course;
import ny.wfh.model.CourseList;
import ny.wfh.model.Student;
import ny.wfh.service.StudentService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private WebClient.Builder webClientBuilder;
	/*@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/service-instances/{applicationName}")
	public List<ServiceInstance> serviceInstancesByApplicationName(
			@PathVariable String applicationName) {
		return this.discoveryClient.getInstances(applicationName);
	}*/
	@RequestMapping("/studentCourses/{studentId}")
	public List<Course> getStudentCoursesById(@PathVariable String studentId)
	{
		/*List<ServiceInstance> instances=serviceInstancesByApplicationName("Course");
		ServiceInstance instance=instances.get(0);*/
		CourseList cList= restTemplate.getForObject("http://Course/courses/student/"+studentId, CourseList.class);
		
		/*CourseList cList=webClientBuilder.build().get().uri(instance.getUri()+"/courses/student/"+studentId)
				.retrieve().bodyToMono(CourseList.class).block();*/
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
		Student student= studentService.getStudent(id);
		
		 List<Course> cList=null;
		
        try {
			ResponseEntity<List<Course>> courseResource = restTemplate.exchange(
					"http://Course/courses", 
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<Course>>() {});
			if(courseResource != null && courseResource.hasBody()){
				cList = courseResource.getBody();
			}
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
        cList.forEach(c -> {
        	System.out.println("CList Course Desc:"+c.getCourseDesc()+ " CList couse Name :"+c.getCourseName()     );
        });
        
        /* final List<Course> finalList=cList;
        
        List<Course> filteredList = student.getCourses().stream()
        	      	.filter(stuCourse -> finalList.stream()
        	        .anyMatch(course -> course.getCourseId().equals(stuCourse.getCourseId())))
        	        .collect(Collectors.toList());*/
        
        
        
        List<Course> filteredCoursesList = cList.stream()
        		.filter(course -> student.getCourses().stream()
        		.anyMatch(stuCourse -> stuCourse.getCourseId().equals(course.getCourseId())))
        		.collect(Collectors.toList());
        
     
        
        filteredCoursesList.forEach(c -> {
        	System.out.println("Course Desc:"+c .getCourseDesc() +" Course Name:"+c.getCourseName()  );
        });
        
		student.setCourses(filteredCoursesList);
		return student;
				
		
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

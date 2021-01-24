package ny.wfh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ny.wfh.model.Course;
import ny.wfh.model.Student;
import ny.wfh.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAllStudents()
	{
		List<Student> studentList = new ArrayList<Student>();
		studentRepository.findAll().forEach(studentList::add);
		/*Student s1= new Student("1","John","Doe static");
		List<Course> cList= new ArrayList<Course>();
		Course c1= new Course("1","Java S","Java Desc");
		cList.add(c1);
		Course c2= new Course("2","Java S","Java S Desc");
		cList.add(c1);
		s1.setCourses(cList);
		studentList.add(s1);*/
		return studentList;
	}
	
	public Student getStudent(String id)
	{
		/*Student s1= new Student("1","John","Doe static");
		List<Course> cList= new ArrayList<Course>();
		Course c1= new Course("1","Java","Java Desc");
		cList.add(c1);
		return s1;*/
		return studentRepository.findById(id).get();
	}
	
	public void addStudent(Student student)
	{
		studentRepository.save(student);
	}
	
	public void updateStudent(Student student)
	{
		studentRepository.save(student);
	}
	
	public void deleteStudent(String id)
	{
		//students.removeIf(c->c.getStudentId().equals(id));
		studentRepository.deleteById(id);
	}


}

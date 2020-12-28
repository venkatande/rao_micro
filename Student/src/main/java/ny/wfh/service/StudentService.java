package ny.wfh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return studentList;
	}
	
	public Student getStudent(String id)
	{
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

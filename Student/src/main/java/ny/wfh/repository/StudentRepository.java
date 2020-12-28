package ny.wfh.repository;

import org.springframework.data.repository.CrudRepository;

import ny.wfh.model.Student;

public interface StudentRepository extends CrudRepository<Student, String>{
	
	

}

package ny.wfh.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ny.wfh.model.Course;

public interface CourseRepository extends CrudRepository<Course,String> {

}
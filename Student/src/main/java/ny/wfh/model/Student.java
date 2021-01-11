package ny.wfh.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Student implements Serializable {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private String studentId;
	private String firstName;
	private String lastName;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Course> courses;
	
	public Student() {}
	public Student(String id,String fName,String lName) {
		this.studentId=id;
		this.firstName=fName;
		this.lastName=lName;
	}
	
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	
	

	
	

}

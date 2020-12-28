package ny.wfh.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class CourseList implements Serializable {
	
	private List<Course> courseList;
	
	public CourseList() {
		courseList=new ArrayList<>();
	}


	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	
	

}
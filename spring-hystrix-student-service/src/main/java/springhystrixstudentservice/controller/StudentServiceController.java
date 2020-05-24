package springhystrixstudentservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springhystrixstudentservice.domain.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentServiceController {

	private static final Map<String, List<Student>> schoolDB;

	static {
		schoolDB = new HashMap<>();

		List<Student> lst = new ArrayList<>();
		Student std = new Student("John", "Class IV");
		lst.add(std);
		std = new Student("Mike", "Class V");
		lst.add(std);

		schoolDB.put("abcschool", lst);

		lst = new ArrayList<>();
		std = new Student("James", "Class III");
		lst.add(std);
		std = new Student("Robert", "Class VI");
		lst.add(std);

		schoolDB.put("xyzschool", lst);

	}

	@GetMapping(value = "/getStudentDetailsForSchool/{schoolName}")
	public List<Student> getStudents(@PathVariable String schoolName) {
		System.out.println("Getting Student details for " + schoolName);

		List<Student> studentList = schoolDB.get(schoolName);
		if (studentList == null) {
			studentList = new ArrayList<>();
			Student std = new Student("Not Found", "N/A");
			studentList.add(std);
		}
		return studentList;
	}
}

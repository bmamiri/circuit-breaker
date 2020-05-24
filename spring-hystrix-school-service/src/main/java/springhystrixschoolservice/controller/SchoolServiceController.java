package springhystrixschoolservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springhystrixschoolservice.delegate.StudentServiceDelegate;

@RestController
public class SchoolServiceController {

	@Autowired
	private StudentServiceDelegate studentServiceDelegate;

	@GetMapping(value = "/getSchoolDetails/{schoolName}")
	public String getStudents(@PathVariable String schoolName) {
		System.out.println("Going to call student service to get data!");
		return studentServiceDelegate.callStudentServiceAndGetData(schoolName);
	}
}

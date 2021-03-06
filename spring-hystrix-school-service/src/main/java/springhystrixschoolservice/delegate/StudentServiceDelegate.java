package springhystrixschoolservice.delegate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class StudentServiceDelegate {
	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "callStudentServiceAndGetData_Fallback")
	public String callStudentServiceAndGetData(String schoolName) {
		System.out.println("Getting School details for " + schoolName);
		String response = restTemplate
				.exchange("http://localhost:8098/getStudentDetailsForSchool/{schoolName}"
						, HttpMethod.GET
						, null
						, new ParameterizedTypeReference<String>() {
						}, schoolName).getBody();

		System.out.println("Response Received as " + response + " -  " + new Date());

		return "NORMAL FLOW !!! - School Name -  " + schoolName + " :::  Student Details " + response + " -  " + new Date();
	}

	private String callStudentServiceAndGetData_Fallback() {
		System.out.println("Student Service is down!!! fallback route enabled...");
		return "CIRCUIT BREAKER ENABLED!!!No Response From Student Service at this moment. Service will be back shortly - " + new Date();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

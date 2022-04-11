package com.cg.springbootrestdatajpa;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.cg.springbootrestdatajpa.model.Employee;

@SpringBootTest(classes = SpringBootRestDatajpaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	private String getRootUrl() 
	{
		return "http://localhost:"+port;
	}
	
	@Test
	public void testCreateEmployee()
	{
		Employee employee = new Employee();
		employee.setFirstName("Testing F-name");
		employee.setLastName("Testing L-name");
		employee.setEmailId("Test@gmail.com");
		ResponseEntity<Employee> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/employees/new", employee, Employee.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
}

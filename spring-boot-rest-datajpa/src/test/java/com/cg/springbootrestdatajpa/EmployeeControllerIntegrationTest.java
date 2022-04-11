package com.cg.springbootrestdatajpa;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

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
		employee.setFirstName("Testing2 F-name");
		employee.setLastName("Testing2 L-name");
		employee.setEmailId("Test2@gmail.com");
		ResponseEntity<Employee> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/employees/new", employee, Employee.class);
		//assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void testGetAllEmployees()
	{
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl()+"/api/employees/all",HttpMethod.GET,entity,String.class);
		assertNotNull(response.getBody());
	}
	
	@Test
	public void testGetEmployeeById()
	{
		Employee employee = restTemplate.getForObject(getRootUrl()+"/api/employees/byid/17", Employee.class);
		assertNotNull(employee);
	}
}

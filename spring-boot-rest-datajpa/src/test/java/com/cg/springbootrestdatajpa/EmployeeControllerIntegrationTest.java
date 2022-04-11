// Working with the real database.

package com.cg.springbootrestdatajpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
		long id=12;
		Employee employee = restTemplate.getForObject(getRootUrl()+"/api/employees/byid/"+id, Employee.class);
		//assertEquals(id, employee.getEmpId());
		assertNotNull(employee);
	}
	
	@Test
	public void testUpdateEmployee()
	{
		long id = 15;
		//Load Object
		Employee employee = restTemplate.getForObject(getRootUrl()+"/api/employees/byid/"+id, Employee.class);
		employee.setFirstName("Update Testing");
		employee.setLastName("Update L-name");
		employee.setEmailId("Update@gmail.com");
		
		restTemplate.put(getRootUrl()+"/api/employees/update/"+id, employee);
		
		Employee updatedEmployee = restTemplate.getForObject(getRootUrl()+"/api/employees/byid/"+id, Employee.class);
		
		assertNotNull(updatedEmployee);
		assertEquals(employee.getFirstName(),updatedEmployee.getFirstName());
	}
	
	@Test
	public void testGetEmployeesByLastName()
	{
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl()+"/api/employees/bylastname/kumar",HttpMethod.GET,entity,String.class);
		assertNotNull(response.getBody());
	}
	
	@Test
	public void testDeleteEmployee()
	{
		long id = 20;
		Employee employee = restTemplate.getForObject(getRootUrl()+"/api/employees/byid/"+id, Employee.class);
		//System.out.println(employee);
		assertEquals(id, employee.getEmpId());
		//assertNotNull(employee);
		
		restTemplate.delete(getRootUrl()+"/api/employees/delete/id/"+id,employee);
		
		// checking for the data is deleted or not
		Employee deletedEmployee = restTemplate.getForObject(getRootUrl()+"/api/employees/byid/"+id, Employee.class);
		assertNotEquals(id, deletedEmployee.getEmpId());
		
		
		/*}catch(final HttpClientErrorException e)
		{
			System.out.println(e.getMessage());
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}*/
	}
	
	@Test
	public void testGetEmployeeByEmailId()
	{
		Employee employee = restTemplate.getForObject(getRootUrl()+"/employees/byemail/Ms@gmail.com", Employee.class);
		assertNotNull(employee);
	}
	
}

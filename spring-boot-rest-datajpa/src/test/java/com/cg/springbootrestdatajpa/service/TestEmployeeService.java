// Testing just by checking the flow, no changes in database
package com.cg.springbootrestdatajpa.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.springbootrestdatajpa.model.Employee;
import com.cg.springbootrestdatajpa.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {
	
	@InjectMocks
	private EmployeeServiceImpl service;
	
	@Mock
	private EmployeeRepository repository;
	
	@Test
	public void testCreateEmployee()
	{
		Employee employee = new Employee(200,"Noob","Gamer","Gamernoob@gmail.com");
		service.createEmployee(employee);
		verify(repository, times(1)).save(employee);
	}
	
	@Test
	public void testGetAllEmployees()
	{
		List<Employee> empList = new ArrayList<Employee>();
		Employee emp1 = new Employee(200,"Noob","Gamer","Gamernoob@gmail.com");
		Employee emp2 = new Employee(201,"WiKD","Wiked","wikd@gmail.com");
		Employee emp3 = new Employee(202,"Shaw","Decked","Sahw@gmail.com");
		
		empList.add(emp1);
		empList.add(emp2);
		empList.add(emp3);
		
		when(repository.findAll()).thenReturn(empList);
		
		List<Employee> employees = service.getAllEmployees();
		assertEquals(3, employees.size());
		verify(repository,times(1)).findAll();
	}
	
	@Test
	public void testGetEmployeeById()
	{
		when(repository.findById(13L)).thenReturn(Optional.of(new Employee(13,"Ayush","Kesarwani","Ayush@gmail.com")));
		
		Optional<Employee> employee = service.getEmployeeById(13L);
		assertEquals(true, employee.isPresent());
	}
	
	@Test
	public void testUpdateEmployee()
	{
		Employee employee = new Employee(13,"Ayush","Kesarwani","Ayush@gmail.com");
		employee.setFirstName("Miku");
		service.updateEmployee(employee);
		assertEquals("Miku",employee.getFirstName());
	}
	
	@Test
	public void testDeleteEmployee()
	{
		Employee employee = new Employee(13,"Ayush","Kesarwani","Ayush@gmail.com");
		
		service.deleteEmployee(employee);
		
		Optional<Employee> emp = service.getEmployeeById(13L);
		assertEquals(false,emp.isPresent());
	}
}

package com.springmvcandjpa.lab2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springmvcandjpa.lab2.exception.TraineeNotFoundException;
import com.springmvcandjpa.lab2.model.Trainee;
import com.springmvcandjpa.lab2.service.TraineeService;

@RestController
@RequestMapping("/api")
public class TraineeController {

	@Autowired
	private TraineeService traineeService;
	
	@PostMapping("/trainee/new")
	public Trainee createNewEmployee(@Valid @RequestBody Trainee trainee)
	{
		return traineeService.createTrainee(trainee);
	}
	@GetMapping("/trainees/all")
	public List<Trainee> viewAllTrainees()
	{
		return traineeService.getAllTrainees();
	}
	@GetMapping("/trainee/byid/{id}")
	public ResponseEntity<Trainee> getTraineeWithSameId(@PathVariable(value="id") int tId) throws TraineeNotFoundException{
		Trainee t = traineeService.getTraineeById(tId).orElseThrow(()->new TraineeNotFoundException("No Trainee with ID:"+tId+" found."));
		return ResponseEntity.ok().body(t);
	}
	@PutMapping("/trainees/update/{id}")
	public ResponseEntity<Trainee> updateTrainee(@PathVariable(value="id") int tId,@Valid @RequestBody Trainee traineeDetails) throws TraineeNotFoundException
	{
		Trainee t = traineeService.getTraineeById(tId).orElseThrow(()-> new TraineeNotFoundException("No Trainee with ID:"+tId+" found."));
		t.setTraineeDomain(traineeDetails.getTraineeDomain());
		t.setTraineeLocation(traineeDetails.getTraineeLocation());
		t.setTraineeName(traineeDetails.getTraineeName());
		
		Trainee updatedTrainee = traineeService.updateTrainee(t);
		return ResponseEntity.ok(updatedTrainee);
	}
	@DeleteMapping("/trainees/delete/{id}")
	public String deleteTrainee(@PathVariable(value="id") int tId) throws TraineeNotFoundException
	{
		Trainee t = traineeService.getTraineeById(tId).orElseThrow(()-> new TraineeNotFoundException("No Trainee with ID:"+tId+" found."));
		traineeService.deleteTrainee(t);
		return "Trainee Details Deleted";
	}
	@GetMapping("/trainee/bydomain/{d}")
	public List<Trainee> traineesByDomain(@PathVariable(value="d") String domain)
	{
		return traineeService.getTraineesByTraineeDomain(domain);
	}
}

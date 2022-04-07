package com.springmvcandjpa.lab2.service;

import java.util.List;
import java.util.Optional;
import com.springmvcandjpa.lab2.model.Trainee;

public interface TraineeService {
	Trainee createTrainee(Trainee trainee);
	List<Trainee> getAllTrainees();
	Optional<Trainee> getTraineeById(int tId);
	Trainee updateTrainee(Trainee trainee);
	void deleteTrainee(Trainee trainee);
	List<Trainee> getTraineesByTraineeDomain(String domain);
}

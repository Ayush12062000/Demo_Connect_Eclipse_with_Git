package com.springmvcandjpa.lab2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvcandjpa.lab2.model.Trainee;
import com.springmvcandjpa.lab2.repository.TraineeDao;

@Service
public class TraineeServiceImpl implements TraineeService{

	@Autowired
	private TraineeDao traineeDao;
	
	@Override
	public Trainee createTrainee(Trainee trainee) {
		return traineeDao.save(trainee);
	}

	@Override
	public List<Trainee> getAllTrainees() {
		return traineeDao.findAll();
	}

	@Override
	public Optional<Trainee> getTraineeById(int tId) {
		return traineeDao.findById(tId);
	}

	@Override
	public Trainee updateTrainee(Trainee trainee) {
		return traineeDao.save(trainee);
	}

	@Override
	public void deleteTrainee(Trainee trainee) {
		traineeDao.delete(trainee);
	}

	@Override
	public List<Trainee> getTraineesByTraineeDomain(String domain) {
		return traineeDao.findByTraineeDomain(domain);
	}

	

}

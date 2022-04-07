package com.springmvcandjpa.lab2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springmvcandjpa.lab2.model.Trainee;


@Repository
public interface TraineeDao extends JpaRepository<Trainee , Integer>{
	List<Trainee> findByTraineeDomain(String traineeDomain);
}

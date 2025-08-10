package com.lavacro.gym.controllers.rest;

import com.lavacro.gym.dao.ExerciseDao;

import com.lavacro.gym.exercises.ExerciseService;
import com.lavacro.gym.model.ExercisesEntity;
import com.lavacro.gym.model.MuscleDTO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class Api {
	private final ExerciseDao exerciseDao;
	private final ExerciseService exerciseService;;

	public Api(
			ExerciseDao exerciseDao,
			ExerciseService exerciseService)
	{
		this.exerciseDao = exerciseDao;
		this.exerciseService = exerciseService;
	}

	@GetMapping(value = "/exercises")
	public ResponseEntity<List<MuscleDTO>> allExercises() {
		final List<MuscleDTO> resp = exerciseDao.allExercises();
		if(resp == null) {
			return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(resp, null, HttpStatus.OK);
	}

	@GetMapping(value = "/months")
	public ResponseEntity<List<String>> allMonths() {
		final List<String> resp = exerciseDao.allMonths();
		if(resp == null) {
			return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(resp, null, HttpStatus.OK);
	}

//	@GetMapping(value = "/stats/{when}")
//	public ResponseEntity<List<ProgressDTO>> stats(@PathVariable("when") final String when) {
//		final List<ProgressDTO> resp = exerciseDao.stats(when);
//		if(resp == null) {
//			return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
//		}
//		return new ResponseEntity<>(resp, null, HttpStatus.OK);
//	}

	@GetMapping(value = "/foo")
	public List<ExercisesEntity> foo() {
		log.info("Hello?");
		return exerciseService.getAllProgress();
	}
}

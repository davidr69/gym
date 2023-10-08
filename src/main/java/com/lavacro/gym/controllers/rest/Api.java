package com.lavacro.gym.controllers.rest;

import com.lavacro.gym.dao.ExerciseDao;

import com.lavacro.gym.dao.MuscleExerciseRepository;
import com.lavacro.gym.model.Exercise;
import com.lavacro.gym.model.Muscle;
import com.lavacro.gym.model.ProgressDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Api {
	private static final Logger logger = LoggerFactory.getLogger(Api.class);
	private final ExerciseDao exerciseDao;
	private final MuscleExerciseRepository muscleExerciseRepository;

	public Api(ExerciseDao exerciseDao, MuscleExerciseRepository muscleExerciseRepository) {
		this.exerciseDao = exerciseDao;
		this.muscleExerciseRepository = muscleExerciseRepository;
	}

	@GetMapping(value = "/exercises")
	public ResponseEntity<List<Muscle>> allExercises() {
		final List<Muscle> resp = exerciseDao.allExercises();
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

	@GetMapping(value = "/stats/{when}")
	public ResponseEntity<List<ProgressDTO>> stats(@PathVariable("when") final String when) {
		final List<ProgressDTO> resp = exerciseDao.stats(when);
		if(resp == null) {
			return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(resp, null, HttpStatus.OK);
	}

	@GetMapping(value = "/foo")
	public List<Exercise> foo() {
		logger.info("Hello?");
		return muscleExerciseRepository.findAll(
				Sort.by(Sort.Direction.ASC, "muscle").and(Sort.by(Sort.Direction.ASC, "exercise"))
		);
	}
}

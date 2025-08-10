package com.lavacro.gym.controllers.rest;

import com.lavacro.gym.model.MuscleDTO;
import com.lavacro.gym.model.ProgressDTO;
import com.lavacro.gym.service.ExerciseService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class Api {
	private final ExerciseService exerciseService;

	public Api(ExerciseService exerciseService) {
		this.exerciseService = exerciseService;
	}

	@GetMapping(value = "/exercises")
	public ResponseEntity<List<MuscleDTO>> allExercises() {
		final List<MuscleDTO> resp = exerciseService.allExercises();
		if(resp == null) {
			return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(resp, null, HttpStatus.OK);
	}

	@GetMapping(value = "/months")
	public ResponseEntity<List<String>> allMonths() {
		final List<String> resp = exerciseService.allMonths();
		if(resp == null) {
			return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(resp, null, HttpStatus.OK);
	}

	@GetMapping(value = "/stats/{when}")
	public ResponseEntity<List<ProgressDTO>> stats(@PathVariable("when") final String when) {
		final List<ProgressDTO> resp = exerciseService.stats(when);
		if(resp == null) {
			return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(resp, null, HttpStatus.OK);
	}
}

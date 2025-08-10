package com.lavacro.gym.exercises;

import com.lavacro.gym.model.ExercisesEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
	private final ProgressRepository progressRepository;

	public ExerciseService(ProgressRepository progressRepository) {
		this.progressRepository = progressRepository;
	}

	public List<ExercisesEntity> getAllProgress() {
		return progressRepository.findAll();
	}
}

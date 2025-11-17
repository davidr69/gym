package com.lavacro.gym.service;

import com.lavacro.gym.repositories.MuscleExerciseRepository;
import com.lavacro.gym.repositories.ProgressRepository;
import com.lavacro.gym.repositories.YrMonRepository;
import com.lavacro.gym.entities.MonthProgressEntity;
import com.lavacro.gym.model.ExerciseDTO;
import com.lavacro.gym.model.MuscleDTO;
import com.lavacro.gym.model.ProgressDTO;
import com.lavacro.gym.model.YrMonDTO;
import com.lavacro.gym.repositories.MonthProgressRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseService {
	private final MuscleExerciseRepository muscleExerciseRepository;
	private final ProgressRepository progressRepository;
	private final YrMonRepository yrMonRepository;
	private final MonthProgressRepository monthProgressRepository;

	public ExerciseService(
			MuscleExerciseRepository muscleExerciseRepository,
			ProgressRepository progressRepository,
			YrMonRepository yrMonRepository,
			MonthProgressRepository monthProgressRepository
	) {
		this.muscleExerciseRepository = muscleExerciseRepository;
		this.progressRepository = progressRepository;
		this.yrMonRepository = yrMonRepository;
		this.monthProgressRepository = monthProgressRepository;
	}

	public List<MuscleDTO> allExercises() {
		/*
		 *     muscle     |             exercise             | id
		 * ---------------+----------------------------------+-----
		 *  Abdominals    | Ab Carver                        |  93
		 *  Abdominals    | Ab Coaster                       |  92
		 *  Biceps        | Biceps curl machine              |  51
		 *  Biceps        | Concentration curl               |  41
		 */

		List<ExerciseDTO> exerciseList = muscleExerciseRepository.getAllExercises();

		List<MuscleDTO> resp = new ArrayList<>();
		MuscleDTO me = null;
		String prevMuscle = null;

		for(ExerciseDTO e: exerciseList) {
			if (prevMuscle == null || !prevMuscle.equals(e.getMuscle())) {
				if (prevMuscle != null) {
					resp.add(me);
				}
				prevMuscle = e.getMuscle();
				me = new MuscleDTO();
				me.setDescription(e.getMuscle());
				me.setExercises(new ArrayList<>());
			}
			ExerciseDTO exercise = new ExerciseDTO();
			exercise.setExerciseName(e.getExerciseName());
			exercise.setId(e.getId());
			me.getExercises().add(exercise);
		}
		resp.add(me);
		return resp;
	}

	public List<ProgressDTO> stats(final String when) {
		ProgressDTO tuple = new ProgressDTO();
		tuple.setMydate(when);
		Example<ProgressDTO> example = Example.of(tuple);
		return progressRepository.findAll(
			example,
			Sort.by(Sort.Direction.ASC, "muscle").and(Sort.by(Sort.Direction.ASC, "exercise"))

		);
	}

	public List<String> allMonths() {
		List<YrMonDTO> data = yrMonRepository.getYrMon();
		return data.stream().map(YrMonDTO::getYrmon).toList();
	}

	public List<MonthProgressEntity> getMonthProgress(Integer year, Integer month) {
		return monthProgressRepository.findByYearAndMonth(year, month);
	}
}

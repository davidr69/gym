package com.lavacro.gym.dao;

import com.lavacro.gym.model.Exercise;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
@ActiveProfiles(profiles = {"local"})
class TestExerciseDaoNative {
	//@Autowired
	@Mock
	private MuscleExerciseRepository muscleExerciseRepository;

	private List<Exercise> exerciseList;

	private void setupExercise() {
		exerciseList = new ArrayList<>();
		Exercise exercise = new Exercise(111, "Abdominals", "Pully crunch");
//		muscleExerciseRepository.save(exercise);
//		exerciseList.add(exercise);

		exercise = new Exercise(95, "Abdominals", "Sit-ups");
//		muscleExerciseRepository.save(exercise);
//		exerciseList.add(exercise);

		exercise = new Exercise(59, "Abdominals", "Torso rotation");
//		muscleExerciseRepository.save(exercise);
//		exerciseList.add(exercise);

		exercise = new Exercise(51, "Biceps", "Biceps curl machine");
//		muscleExerciseRepository.save(exercise);
//		exerciseList.add(exercise);

		exercise = new Exercise(41, "Biceps", "Concentration curl");
//		muscleExerciseRepository.save(exercise);
//		exerciseList.add(exercise);

		exercise = new Exercise(78, "Biceps", "Cross-body hammer curls");
//		muscleExerciseRepository.save(exercise);
//		exerciseList.add(exercise);
	}
	@Test
	void testAllExercises() {
		setupExercise();
		when(muscleExerciseRepository.getAllExercises()).thenReturn(exerciseList);
		List<Exercise> exerciseList = muscleExerciseRepository.getAllExercises();
//		Assertions.assertEquals(6, exerciseList.size());
//		Assertions.assertEquals("Biceps", exerciseList.get(3).getMuscle());
//		Assertions.assertEquals("Sit-ups", exerciseList.get(1).getExerciseName());
	}
}

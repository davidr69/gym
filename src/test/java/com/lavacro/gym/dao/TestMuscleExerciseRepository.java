package com.lavacro.gym.dao;

import com.lavacro.gym.model.Exercise;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TestMuscleExerciseRepository {
	@Autowired
	private MuscleExerciseRepository muscleExerciseRepository;

	@Test
	public void testRepo() {
		muscleExerciseRepository.save(
			new Exercise(1, "Abs", "Torso Rotation")
		);
		Example<Exercise> example = Example.of(new Exercise(1, "Abs", "Torso Rotation"));
		muscleExerciseRepository.findAll(example);
	}
}

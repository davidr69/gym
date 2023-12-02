package com.lavacro.gym.dao;

import com.lavacro.gym.model.Exercise;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
@ActiveProfiles(profiles = {"local"})
class TestMuscleExerciseRepository {
	@Autowired
	private MuscleExerciseRepository muscleExerciseRepository;

	@Test
	void testRepo() {
		muscleExerciseRepository.save(
			new Exercise(1, "Abs", "Torso Rotation")
		);
		muscleExerciseRepository.save(
				new Exercise(2, "Abs", "Hanging Knee Raise")
		);
		List<Exercise> exerciseList = muscleExerciseRepository.findAll();
		Assertions.assertEquals(2, exerciseList.size());

		Example<Exercise> example = Example.of(new Exercise(1, "Abs", "Torso Rotation"));
		Optional<Exercise> optional = muscleExerciseRepository.findOne(example);

		Assertions.assertTrue(optional.isPresent());
	}
}

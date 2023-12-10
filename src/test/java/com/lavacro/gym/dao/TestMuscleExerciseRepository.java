package com.lavacro.gym.dao;

import com.lavacro.gym.model.ExerciseDTO;
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
			new ExerciseDTO(1, "Abs", "Torso Rotation")
		);
		muscleExerciseRepository.save(
				new ExerciseDTO(2, "Abs", "Hanging Knee Raise")
		);
		List<ExerciseDTO> exerciseList = muscleExerciseRepository.findAll();
		Assertions.assertEquals(2, exerciseList.size());

		Example<ExerciseDTO> example = Example.of(new ExerciseDTO(1, "Abs", "Torso Rotation"));
		Optional<ExerciseDTO> optional = muscleExerciseRepository.findOne(example);

		Assertions.assertTrue(optional.isPresent());
	}
}

package com.lavacro.gym.dao;

import com.lavacro.gym.model.ExerciseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TestExerciseDaoNative {
	@Autowired private MuscleExerciseRepository muscleExerciseRepository;

	@Test
	void testAllExercises() {
		List<ExerciseDTO> exerciseList = muscleExerciseRepository.getAllExercises();
		Assertions.assertEquals(114, exerciseList.size());
	}
}

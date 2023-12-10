package com.lavacro.gym.dao;

import com.lavacro.gym.model.ProgressDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TestExerciseDao {
	@Autowired
	private ExerciseDao exerciseDao;

	@Test
	void testStats() {
		List<ProgressDTO> dto = exerciseDao.stats("202311");
		Assertions.assertEquals(2, dto.size());
	}
}

package com.lavacro.gym.dao;

import com.lavacro.gym.model.ProgressDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
@ActiveProfiles(profiles = {"local"})
class TestExerciseDao {
	@Autowired
	private ProgressRepository progressRepository;

	@Autowired
	private ExerciseDao exerciseDao;

	private void setup() {
		ProgressDTO progressDTO = new ProgressDTO();
		progressDTO.setProgressId(1);
		progressDTO.setExerciseId(1);
		progressDTO.setExercise("cross-body hammer curl");
		progressDTO.setMuscle("biceps");
		progressDTO.setMydate("202311");
		progressDTO.setWeight(new BigDecimal("45.0"));
		progressDTO.setRep1(4);
		progressRepository.save(progressDTO);

		progressDTO = new ProgressDTO();
		progressDTO.setProgressId(2);
		progressDTO.setExerciseId(2);
		progressDTO.setExercise("hanging Knee raise");
		progressDTO.setMuscle("Abs");
		progressDTO.setMydate("202311");
		progressDTO.setRep1(18);
		progressDTO.setRep2(15);
		progressRepository.save(progressDTO);

		progressDTO = new ProgressDTO();
		progressDTO.setProgressId(3);
		progressDTO.setExerciseId(4);
		progressDTO.setExercise("smith machine bench press");
		progressDTO.setMuscle("Chest");
		progressDTO.setMydate("202310");
		progressDTO.setWeight(new BigDecimal("140.0"));
		progressDTO.setRep1(5);
		progressRepository.save(progressDTO);
	}

	@Test
	void testStats() {
		setup();
		List<ProgressDTO> dto = exerciseDao.stats("202311");
		Assertions.assertEquals(2, dto.size());
	}
}

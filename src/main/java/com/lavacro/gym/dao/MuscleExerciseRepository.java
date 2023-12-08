package com.lavacro.gym.dao;

import com.lavacro.gym.model.ExerciseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuscleExerciseRepository extends JpaRepository<ExerciseDTO, Integer> {
	@Query(value = """
		SELECT m.description AS muscle, e.id, e.description AS exercise_name
		FROM exercise e
		JOIN muscle m ON e.muscle = m.id
		ORDER BY m.description, exercise_name
	""", nativeQuery = true)
	List<ExerciseDTO> getAllExercises();
}

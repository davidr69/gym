package com.lavacro.gym.dao;

import com.lavacro.gym.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuscleExerciseRepository extends JpaRepository<Exercise, Integer> {
	@Query(value = """
		SELECT m.description AS muscle, e.description AS exercise, e.id
		FROM exercise e
		JOIN muscle m ON e.muscle = m.id
		ORDER BY m.description, e.description
	""", nativeQuery = true)
	List<Exercise> getAllExercises();
}

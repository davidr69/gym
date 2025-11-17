package com.lavacro.gym.repositories;

import com.lavacro.gym.entities.MonthProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthProgressRepository extends JpaRepository<MonthProgressEntity,Integer> {
	@Query(value = """
		SELECT e.id, m.description AS muscle, m.id AS muscle_id, e.description AS exercise, p.weight, p.rep1, p.rep2
		FROM app.exercise e
		JOIN app.muscle m ON e.muscle = m.id
		LEFT JOIN app.progress p ON e.id = p.exercise
		    AND DATE_PART('year', mydate) = :year
		    AND DATE_PART('month', mydate) = :month
		ORDER BY muscle, exercise
	""", nativeQuery = true)
	List<MonthProgressEntity> findByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month);
}

package com.lavacro.gym.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MonthProgressEntity {
	@Id
	private Long id;

	private String muscle;

	@Column(name = "muscle_id")
	private Integer muscleId;

	private String exercise;

	private Float weight;

	private Integer rep1;

	private Integer rep2;


/*
SELECT e.id, m.description AS muscle, m.id AS muscle_id, e.description AS exercise, p.weight, p.rep1, p.rep2
FROM app.exercise e
JOIN app.muscle m ON e.muscle = m.id
LEFT JOIN app.progress p ON e.id = p.exercise
    AND DATE_PART('year', mydate) = 2025
    AND DATE_PART('month', mydate) = 10
ORDER BY muscle, exercise;

 */

}

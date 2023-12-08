package com.lavacro.gym.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ExerciseDTO {
	@Id
	private Integer id;
	private String muscle;
	@Column(name = "exercise_name")
	private String exerciseName;
}

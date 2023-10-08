package com.lavacro.gym.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Exercise {
	@Id
	private Integer id;
	private String muscle;
	private String exercise;
}

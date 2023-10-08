package com.lavacro.gym.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Muscle {
	private String description;
	private List<Exercise> exercises;
}

package com.lavacro.gym.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
class Exercise {
	private String exercise;
	private List<Progress> progress;
}

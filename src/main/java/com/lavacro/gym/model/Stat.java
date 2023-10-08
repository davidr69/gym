package com.lavacro.gym.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Stat {
	private String exercise;
	private Integer exerciseId;
	private String muscle;
	private String when;
	private BigDecimal weight;
	private Integer rep1;
	private Integer rep2;
	private Integer progId;

}

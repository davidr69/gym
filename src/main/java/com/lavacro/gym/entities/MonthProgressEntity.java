package com.lavacro.gym.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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

	private BigDecimal weight;

	private Integer rep1;

	private Integer rep2;

	@Column(name = "progress_id")
	private Integer progId;
}

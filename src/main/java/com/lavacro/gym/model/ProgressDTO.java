package com.lavacro.gym.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "allprogress")
public class ProgressDTO {
	@Id
	@Column(name = "progid")
	private Integer progressId;

	@Column(name = "exerciseid")
	private Integer exerciseId;

	private String exercise;
	private String muscle;
	private String mydate;
	private BigDecimal weight;
	private Integer rep1;
	private Integer rep2;
}

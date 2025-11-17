package com.lavacro.gym.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "progress", schema = "app")
public class ProgressEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
	@SequenceGenerator(name = "sequence_generator", sequenceName = "app.prog_seq", allocationSize = 1)

	private Integer id;

	private Integer exercise;

	private Instant mydate;

	private Integer rep1;

	private Integer rep2;

	private BigDecimal weight;
}

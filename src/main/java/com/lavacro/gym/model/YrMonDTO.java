package com.lavacro.gym.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class YrMonDTO {
	@Id
	private String yrmon;
}

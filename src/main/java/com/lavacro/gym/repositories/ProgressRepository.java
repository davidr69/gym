package com.lavacro.gym.repositories;

import com.lavacro.gym.model.ProgressDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressRepository extends JpaRepository<ProgressDTO, Integer> {
	ProgressDTO findByProgressId(Integer id);
}

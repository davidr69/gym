package com.lavacro.gym.repositories;

import com.lavacro.gym.entities.ProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressRepository extends JpaRepository<ProgressEntity, Integer> {
}

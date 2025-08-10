package com.lavacro.gym.dao;

import com.lavacro.gym.model.YrMonDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YrMonRepository extends JpaRepository<YrMonDTO, String> {
	@Query(value = """
		WITH x AS (SELECT DISTINCT mydate FROM progress)
		SELECT CAST(date_part('year', mydate) AS varchar) || LPAD(CAST(date_part('month', mydate) AS varchar), 2, '0') AS yrmon
		FROM x
		ORDER BY mydate
	""", nativeQuery = true)
	List<YrMonDTO> getYrMon();
}

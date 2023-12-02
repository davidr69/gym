package com.lavacro.gym.dao;

import com.lavacro.gym.model.Exercise;
import com.lavacro.gym.model.Muscle;
import com.lavacro.gym.model.ProgressDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExerciseDao {
	private static final Logger logger = LoggerFactory.getLogger(ExerciseDao.class);

	private final DataSource dataSource;
	private final MuscleExerciseRepository muscleExerciseRepository;

	private final ProgressRepository progressRepository;

	public ExerciseDao(
			DataSource dataSource,
			ProgressRepository progressRepository,
			MuscleExerciseRepository muscleExerciseRepository
	) {

		this.dataSource = dataSource;
		this.progressRepository = progressRepository;
		this.muscleExerciseRepository = muscleExerciseRepository;
	}

	public List<Muscle> allExercises() {
		/*
		 *     muscle     |             exercise             | id
		 * ---------------+----------------------------------+-----
		 *  Abdominals    | Ab Carver                        |  93
		 *  Abdominals    | Ab Coaster                       |  92
		 *  Biceps        | Biceps curl machine              |  51
		 *  Biceps        | Concentration curl               |  41
		 */

		List<Exercise> exerciseList = muscleExerciseRepository.getAllExercises();

		List<Muscle> resp = new ArrayList<>();
		Muscle me = null;
		String prevMuscle = null;

		for(Exercise e: exerciseList) {
			if (prevMuscle == null || !prevMuscle.equals(e.getMuscle())) {
				if (prevMuscle != null) {
					resp.add(me);
				}
				prevMuscle = e.getMuscle();
				me = new Muscle();
				me.setDescription(e.getMuscle());
				me.setExercises(new ArrayList<>());
			}
			Exercise exercise = new Exercise();
			exercise.setExerciseName(e.getExerciseName());
			exercise.setId(e.getId());
			me.getExercises().add(exercise);
		}
		resp.add(me);
		return resp;
	}

	public List<String> allMonths() {
		String sql =
				"WITH x AS (SELECT DISTINCT mydate FROM progress) " +
				"SELECT CAST(date_part('year', mydate) AS varchar) || LPAD(CAST(date_part('month', mydate) AS varchar), 2, '0') AS yrmon " +
				"FROM x " +
				"ORDER BY mydate";

		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()
		) {
			List<String> resp = new ArrayList<>();
			while(rs.next()) {
				resp.add(rs.getString("yrmon"));
			}
			return resp;
		} catch (SQLException e) {
			logger.error("SQL exception: {}", e.getMessage());
			return new ArrayList<>();
		}
	}

	public List<ProgressDTO> stats(final String when) {
		/*
			SELECT exercise, exerciseID, muscle, mydate, weight, rep1, rep2, progid
			FROM allprogress
			WHERE mydate = ?
			ORDER BY muscle, exercise
		*/

		ProgressDTO tuple = new ProgressDTO();
		tuple.setMydate(when);
		Example<ProgressDTO> example = Example.of(tuple);
		return progressRepository.findAll(
				example,
				Sort.by(Sort.Direction.ASC, "muscle").and(Sort.by(Sort.Direction.ASC, "exercise"))

		);
	}
}

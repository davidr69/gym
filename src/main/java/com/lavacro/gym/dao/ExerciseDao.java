package com.lavacro.gym.dao;

import com.lavacro.gym.model.ExerciseDTO;
import com.lavacro.gym.model.MuscleDTO;
import com.lavacro.gym.model.ProgressDTO;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ExerciseDao {
	private final DataSource dataSource;
	private final MuscleExerciseRepository muscleExerciseRepository;
	private final ProgressRepository progressRepository;

	public ExerciseDao(
			DataSource dataSource,
			MuscleExerciseRepository muscleExerciseRepository,
			ProgressRepository progressRepository
	) {

		this.dataSource = dataSource;
		this.muscleExerciseRepository = muscleExerciseRepository;
		this.progressRepository = progressRepository;
	}

	public List<MuscleDTO> allExercises() {
		/*
		 *     muscle     |             exercise             | id
		 * ---------------+----------------------------------+-----
		 *  Abdominals    | Ab Carver                        |  93
		 *  Abdominals    | Ab Coaster                       |  92
		 *  Biceps        | Biceps curl machine              |  51
		 *  Biceps        | Concentration curl               |  41
		 */

		List<ExerciseDTO> exerciseList = muscleExerciseRepository.getAllExercises();

		List<MuscleDTO> resp = new ArrayList<>();
		MuscleDTO me = null;
		String prevMuscle = null;

		for(ExerciseDTO e: exerciseList) {
			if (prevMuscle == null || !prevMuscle.equals(e.getMuscle())) {
				if (prevMuscle != null) {
					resp.add(me);
				}
				prevMuscle = e.getMuscle();
				me = new MuscleDTO();
				me.setDescription(e.getMuscle());
				me.setExercises(new ArrayList<>());
			}
			ExerciseDTO exercise = new ExerciseDTO();
			exercise.setExerciseName(e.getExerciseName());
			exercise.setId(e.getId());
			me.getExercises().add(exercise);
		}
		resp.add(me);
		return resp;
	}

	public List<String> allMonths() {
		String sql = """
			WITH x AS (SELECT DISTINCT mydate FROM progress)
			SELECT CAST(date_part('year', mydate) AS varchar) || LPAD(CAST(date_part('month', mydate) AS varchar), 2, '0') AS yrmon
			FROM x
			ORDER BY mydate
		""";

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
			log.error("SQL exception: {}", e.getMessage());
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

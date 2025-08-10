package com.lavacro.gym.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@Setter
@Document(collection = "exercises")
public class ExercisesEntity {
	@MongoId
	private String id;
	private String muscle;
	private List<Exercise> exercises;
}
/*
[
  {
    "muscle":"abdominals",
    "exercises":[
      {
        "exercise":"hanging knee raise",
        "progress":[
          {
            "date":"202104",
            "rep1":20,
            "rep2":15
          },{
            "date":"202203",
            "rep1":21,
            "rep2":16
          }
        ]
      },{
        "exercise":"torso rotation",
        "progress":[
          {
            "date":"202203",
            "weight":170,
            "rep1":14
          },{
            "date":"202204",
            "weight":190,
            "rep1":21
          }
        ]
      }
    ]
  },{
    "muscle":"biceps",
    "exercises":[
      "exercise":"hammer curl",
      "progress":[
        {
          "date":"202306",
          "weight":45,
          "rep1":7
        }
      ]
    ]
  }
]
 */
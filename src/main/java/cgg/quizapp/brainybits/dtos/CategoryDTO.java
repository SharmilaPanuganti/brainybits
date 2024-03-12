package cgg.quizapp.brainybits.dtos;

import cgg.quizapp.brainybits.entities.QuizQuestion;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDTO {

  private int cat_id;

  private String name;

  private List<QuizQuestion> questions;
}

package cgg.quizapp.brainybits.dtos;

import cgg.quizapp.brainybits.entities.Category;
import java.util.HashMap;
import java.util.Map;
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
public class QuizQuestionDTO {

  private Long id;

  private String question;

  private Map<Integer, String> options = new HashMap<>();

  private int correctOption;

  // Constructors, getters, and setters

  private Category category;
}

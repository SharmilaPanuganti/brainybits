package cgg.quizapp.brainybits.dtos;

import cgg.quizapp.brainybits.entities.Category;
import cgg.quizapp.brainybits.entities.QuizQuestion;
import cgg.quizapp.brainybits.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

  private User user;

  private QuizQuestion question;

  private int answer;

  private Category category;
}

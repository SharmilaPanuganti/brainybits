package cgg.quizapp.brainybits.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class UserResponse {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;

  @ManyToOne
  private User user;

  @OneToOne
  private QuizQuestion question;

  private int answer;

  @ManyToOne
  private Category category;
}

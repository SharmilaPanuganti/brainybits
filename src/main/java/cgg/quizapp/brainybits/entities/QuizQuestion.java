package cgg.quizapp.brainybits.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quiz_questions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String question;

  @ElementCollection
  @CollectionTable(
    name = "quiz_question_options",
    joinColumns = @JoinColumn(name = "question_id")
  )
  @MapKeyColumn(name = "option_number")
  @Column(name = "option_value", nullable = false)
  private Map<Integer, String> options = new HashMap<>();

  @Column(nullable = false)
  private int correctOption;

  // Constructors, getters, and setters
  @ManyToOne
  @JoinColumn(name = "cat_id")
  @JsonIgnore
  private Category category;
}

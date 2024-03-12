package cgg.quizapp.brainybits.repositories;

import cgg.quizapp.brainybits.entities.Category;
import cgg.quizapp.brainybits.entities.QuizQuestion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Quizrepository extends JpaRepository<QuizQuestion, Integer> {
  List<QuizQuestion> findByCategory(Category category);
}

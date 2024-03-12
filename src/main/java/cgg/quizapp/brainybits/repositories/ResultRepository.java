package cgg.quizapp.brainybits.repositories;

import cgg.quizapp.brainybits.entities.Category;
import cgg.quizapp.brainybits.entities.Result;
import cgg.quizapp.brainybits.entities.Resultpk;
import cgg.quizapp.brainybits.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Resultpk> {
  List<Result> findByUser(User user);
  List<Result> findByCategory(Category category);
}

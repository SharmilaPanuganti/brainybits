package cgg.quizapp.brainybits.repositories;

import cgg.quizapp.brainybits.entities.Category;
import cgg.quizapp.brainybits.entities.User;
import cgg.quizapp.brainybits.entities.UserResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserResponseRepo extends JpaRepository<UserResponse, Integer> {
  List<UserResponse> findByUserAndCategory(User user, Category category);
}

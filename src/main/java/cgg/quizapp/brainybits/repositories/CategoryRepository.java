package cgg.quizapp.brainybits.repositories;

import cgg.quizapp.brainybits.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {}

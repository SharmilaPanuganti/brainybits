package cgg.quizapp.brainybits.services;

import cgg.quizapp.brainybits.dtos.CategoryDTO;
import cgg.quizapp.brainybits.exceptions.ResourceNotFoundException;
import java.util.List;

public interface CategoryService {
  public CategoryDTO addCategory(CategoryDTO categoryDTO);

  public List<CategoryDTO> getAllCategories();

  public void deleteCategory(int id);

  public CategoryDTO updateCategory(int id, CategoryDTO categoryDTO);

  public CategoryDTO getCategoryById(int id) throws ResourceNotFoundException;
}

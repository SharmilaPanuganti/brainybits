package cgg.quizapp.brainybits.services.impl;

import cgg.quizapp.brainybits.dtos.CategoryDTO;
import cgg.quizapp.brainybits.entities.Category;
import cgg.quizapp.brainybits.entities.QuizQuestion;
import cgg.quizapp.brainybits.exceptions.ResourceNotFoundException;
import cgg.quizapp.brainybits.repositories.CategoryRepository;
import cgg.quizapp.brainybits.services.CategoryService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository catRepo;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public CategoryDTO addCategory(CategoryDTO categoryDTO) {
    Category category = modelMapper.map(categoryDTO, Category.class);
    List<QuizQuestion> questions = category.getQuestions();
    if (questions != null) questions.forEach(question ->
      question.setCategory(category)
    );
    category.setQuestions(questions);
    Category quiz = catRepo.save(category);
    return modelMapper.map(quiz, CategoryDTO.class);
  }

  @Override
  public List<CategoryDTO> getAllCategories() {
    List<Category> all = catRepo.findAll();
    List<CategoryDTO> list = all
      .stream()
      .map(category -> modelMapper.map(category, CategoryDTO.class))
      .toList();
    return list;
  }

  @Override
  public void deleteCategory(int id) {
    Category category = catRepo.findById(id).orElseThrow();
    catRepo.delete(category);
  }

  @Override
  public CategoryDTO updateCategory(int id, CategoryDTO updateCategory) {
    Category cat = catRepo.findById(id).orElseThrow();
    cat.setName(updateCategory.getName());
    cat.setQuestions(updateCategory.getQuestions());
    Category save = catRepo.save(cat);
    return modelMapper.map(save, CategoryDTO.class);
  }

  @Override
  public CategoryDTO getCategoryById(int id) throws ResourceNotFoundException {
    Category category = catRepo
      .findById(id)
      .orElseThrow(() ->
        new ResourceNotFoundException("category", "id", id + "")
      );
    return modelMapper.map(category, CategoryDTO.class);
  }
}

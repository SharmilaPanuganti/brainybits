package cgg.quizapp.brainybits.services.impl;

import cgg.quizapp.brainybits.dtos.QuizQuestionDTO;
import cgg.quizapp.brainybits.entities.Category;
import cgg.quizapp.brainybits.entities.QuizQuestion;
import cgg.quizapp.brainybits.exceptions.ResourceNotFoundException;
import cgg.quizapp.brainybits.repositories.CategoryRepository;
import cgg.quizapp.brainybits.repositories.Quizrepository;
import cgg.quizapp.brainybits.services.QuizService;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceImpl implements QuizService {

  @Autowired
  private Quizrepository quizRepo;

  @Autowired
  private CategoryRepository catRepo;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public QuizQuestionDTO addQuestion(QuizQuestionDTO question) {
    QuizQuestion save = quizRepo.save(
      modelMapper.map(question, QuizQuestion.class)
    );
    return modelMapper.map(save, QuizQuestionDTO.class);
  }

  @Override
  public QuizQuestionDTO getQuestionById(int id)
    throws ResourceNotFoundException {
    QuizQuestion quiz = quizRepo
      .findById(id)
      .orElseThrow(() ->
        new ResourceNotFoundException("Question", "id", id + "")
      );
    return modelMapper.map(quiz, QuizQuestionDTO.class);
  }

  @Override
  public List<QuizQuestionDTO> getQuestionsByCategory(int catId) {
    Category category = catRepo.findById(catId).orElseThrow();
    List<QuizQuestionDTO> questions = quizRepo
      .findByCategory(category)
      .stream()
      .map(question -> modelMapper.map(question, QuizQuestionDTO.class))
      .toList();
    return questions;
  }

  @Override
  public void deleteQuestion(int id) {
    QuizQuestion question = quizRepo.findById(id).orElseThrow();
    quizRepo.delete(question);
  }

  @Override
  public QuizQuestionDTO updateQuestion(int id, QuizQuestionDTO updated) {
    QuizQuestion question = quizRepo.findById(id).orElseThrow();
    question.setCorrectOption(updated.getCorrectOption());
    question.setOptions(updated.getOptions());
    question.setQuestion(updated.getQuestion());
    question.setCategory(updated.getCategory());
    QuizQuestion save = quizRepo.save(question);
    return modelMapper.map(save, QuizQuestionDTO.class);
  }
}

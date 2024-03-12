package cgg.quizapp.brainybits.services;

import cgg.quizapp.brainybits.dtos.QuizQuestionDTO;
import cgg.quizapp.brainybits.exceptions.ResourceNotFoundException;
import java.util.List;

public interface QuizService {
  public QuizQuestionDTO addQuestion(QuizQuestionDTO question);

  public List<QuizQuestionDTO> getQuestionsByCategory(int catId);

  public void deleteQuestion(int id);

  public QuizQuestionDTO updateQuestion(int id, QuizQuestionDTO updated);

  public QuizQuestionDTO getQuestionById(int id)
    throws ResourceNotFoundException;
}

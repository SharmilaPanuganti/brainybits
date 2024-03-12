package cgg.quizapp.brainybits.services.impl;

import cgg.quizapp.brainybits.dtos.CategoryDTO;
import cgg.quizapp.brainybits.dtos.UserResponseDTO;
import cgg.quizapp.brainybits.entities.Category;
import cgg.quizapp.brainybits.entities.QuizQuestion;
import cgg.quizapp.brainybits.entities.User;
import cgg.quizapp.brainybits.entities.UserResponse;
import cgg.quizapp.brainybits.exceptions.ResourceNotFoundException;
import cgg.quizapp.brainybits.repositories.UserResponseRepo;
import cgg.quizapp.brainybits.services.UserResponseService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserResponseServiceImpl implements UserResponseService {

  @Autowired
  private UserResponseRepo userResponseRepo;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public UserResponseDTO addResponse(UserResponseDTO userResponse) {
    UserResponse up = modelMapper.map(userResponse, UserResponse.class);
    UserResponse save = userResponseRepo.save(up);
    return modelMapper.map(save, UserResponseDTO.class);
  }

  @Override
  public void deleteResponse(int id) throws ResourceNotFoundException {
    userResponseRepo
      .findById(id)
      .orElseThrow(() ->
        new ResourceNotFoundException("UserResponse", "id", id + "")
      );
    userResponseRepo.deleteById(id);
  }

  @Override
  public void updateResponse(int id, UserResponseDTO userReposnse)
    throws ResourceNotFoundException {
    UserResponse up = userResponseRepo
      .findById(id)
      .orElseThrow(() ->
        new ResourceNotFoundException("UserResponse", "id", id + "")
      );
    up.setAnswer(userReposnse.getAnswer());
    up.setQuestion(
      modelMapper.map(userReposnse.getQuestion(), QuizQuestion.class)
    );
    up.setUser(userReposnse.getUser());
    up.setCategory(modelMapper.map(userReposnse.getCategory(), Category.class));
    UserResponse save = userResponseRepo.save(up);
  }

  @Override
  public List<UserResponseDTO> getResponseByCategoryandUser(
    User user,
    CategoryDTO category
  ) {
    List<UserResponse> userResponses = userResponseRepo.findByUserAndCategory(
      user,
      modelMapper.map(category, Category.class)
    );
    List<UserResponseDTO> list = userResponses
      .stream()
      .map(userResponse -> modelMapper.map(userResponse, UserResponseDTO.class))
      .toList();
    return list;
  }
}

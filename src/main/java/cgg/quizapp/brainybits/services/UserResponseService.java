package cgg.quizapp.brainybits.services;

import cgg.quizapp.brainybits.dtos.CategoryDTO;
import cgg.quizapp.brainybits.dtos.UserResponseDTO;
import cgg.quizapp.brainybits.entities.Category;
import cgg.quizapp.brainybits.entities.User;
import cgg.quizapp.brainybits.exceptions.ResourceNotFoundException;
import java.util.List;

public interface UserResponseService {
  public UserResponseDTO addResponse(UserResponseDTO userResponse);

  public void deleteResponse(int id) throws ResourceNotFoundException;

  public void updateResponse(int id, UserResponseDTO userReposnse)
    throws ResourceNotFoundException;

  public List<UserResponseDTO> getResponseByCategoryandUser(
    User user,
    CategoryDTO category
  );
}

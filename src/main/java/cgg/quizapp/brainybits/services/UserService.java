package cgg.quizapp.brainybits.services;

import cgg.quizapp.brainybits.entities.User;
import cgg.quizapp.brainybits.exceptions.ResourceNotFoundException;
import java.util.List;

public interface UserService {
  List<User> getAllUsers();
  User addUser(User user);
  void deleteUser(int id) throws ResourceNotFoundException;
  User updateUser(int id, User user);
  User getUserById(int id);
}

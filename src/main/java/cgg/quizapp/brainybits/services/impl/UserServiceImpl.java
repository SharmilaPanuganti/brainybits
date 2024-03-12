package cgg.quizapp.brainybits.services.impl;

import cgg.quizapp.brainybits.entities.User;
import cgg.quizapp.brainybits.exceptions.ResourceNotFoundException;
import cgg.quizapp.brainybits.repositories.UserRepository;
import cgg.quizapp.brainybits.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepo;

  @Override
  public List<User> getAllUsers() {
    List<User> users = userRepo.findAll();
    return users;
  }

  @Override
  public User addUser(User user) {
    User save = userRepo.save(user);
    return save;
  }

  @Override
  public void deleteUser(int id) throws ResourceNotFoundException {
    User user = userRepo
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("user", "id", "" + id));
    userRepo.delete(user);
  }

  @Override
  public User updateUser(int id, User updateUser) {
    User user = userRepo.findById(id).orElseThrow();
    user.setEmail(updateUser.getEmail());
    user.setPassword(updateUser.getPassword());
    user.setUserName(updateUser.getUserName());
    User savedUser = userRepo.save(user);
    return savedUser;
  }

  @Override
  public User getUserById(int id) {
    User user = userRepo.findById(id).orElseThrow();
    return user;
  }
}

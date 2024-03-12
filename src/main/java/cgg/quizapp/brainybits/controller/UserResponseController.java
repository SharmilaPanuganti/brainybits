package cgg.quizapp.brainybits.controller;

import cgg.quizapp.brainybits.dtos.CategoryDTO;
import cgg.quizapp.brainybits.dtos.QuizQuestionDTO;
import cgg.quizapp.brainybits.dtos.UserResponseDTO;
import cgg.quizapp.brainybits.entities.Category;
import cgg.quizapp.brainybits.entities.QuizQuestion;
import cgg.quizapp.brainybits.entities.User;
import cgg.quizapp.brainybits.exceptions.ResourceNotFoundException;
import cgg.quizapp.brainybits.services.CategoryService;
import cgg.quizapp.brainybits.services.QuizService;
import cgg.quizapp.brainybits.services.UserResponseService;
import cgg.quizapp.brainybits.services.UserService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserResponseController {

  @Autowired
  private QuizService quizService;

  @Autowired
  private UserService userService;

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private UserResponseService userResponseService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping("/addResponse/{uid}/{cid}/{qid}/{ans}")
  public ResponseEntity<UserResponseDTO> getResponse(
    @PathVariable int qid,
    @PathVariable int uid,
    @PathVariable int cid,
    @PathVariable int ans
  ) throws ResourceNotFoundException {
    QuizQuestionDTO question = quizService.getQuestionById(qid);
    User user = userService.getUserById(uid);
    CategoryDTO category = categoryService.getCategoryById(cid);
    UserResponseDTO userResponse = new UserResponseDTO(
      user,
      modelMapper.map(question, QuizQuestion.class),
      ans,
      modelMapper.map(category, Category.class)
    );
    UserResponseDTO response = userResponseService.addResponse(userResponse);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/getResponse/{uid}/{cid}")
  public ResponseEntity<List<UserResponseDTO>> getResponseByUserandCategory(
    @PathVariable int uid,
    @PathVariable int cid
  ) throws ResourceNotFoundException {
    User user = userService.getUserById(uid);
    CategoryDTO category = categoryService.getCategoryById(cid);
    List<UserResponseDTO> response = userResponseService.getResponseByCategoryandUser(
      user,
      category
    );
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/deleteResponse/{id}")
  public ResponseEntity<String> deleteResponse(@PathVariable int id)
    throws ResourceNotFoundException {
    userResponseService.deleteResponse(id);
    return ResponseEntity.ok("Deleted successfully");
  }
}

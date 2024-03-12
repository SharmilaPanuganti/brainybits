package cgg.quizapp.brainybits.controller;

import cgg.quizapp.brainybits.dtos.CategoryDTO;
import cgg.quizapp.brainybits.entities.Category;
import cgg.quizapp.brainybits.entities.Result;
import cgg.quizapp.brainybits.entities.Resultpk;
import cgg.quizapp.brainybits.entities.User;
import cgg.quizapp.brainybits.exceptions.ResourceNotFoundException;
import cgg.quizapp.brainybits.services.CategoryService;
import cgg.quizapp.brainybits.services.ResultService;
import cgg.quizapp.brainybits.services.UserService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class ResultServiceController {

  @Autowired
  private ResultService resultService;

  @Autowired
  private UserService userService;

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping("/addResult/{uid}/{cid}")
  public ResponseEntity<Result> postResult(
    @RequestBody int score,
    @PathVariable int uid,
    @PathVariable int cid
  ) throws ResourceNotFoundException {
    User user = userService.getUserById(uid);
    CategoryDTO category = categoryService.getCategoryById(cid);
    String pass = score > 15 ? "pass" : "fail";
    Result result = new Result(
      score,
      pass,
      user,
      modelMapper.map(category, Category.class)
    );
    Result result2 = resultService.addResult(result);

    return ResponseEntity.ok(result2);
  }

  @GetMapping("/getResults")
  public ResponseEntity<List<Result>> getResults() {
    List<Result> results = resultService.getResults();
    return ResponseEntity.ok(results);
  }

  @GetMapping("/getResults/user/{uid}")
  public ResponseEntity<List<Result>> getUserResults(@PathVariable int uid) {
    User user = userService.getUserById(uid);
    List<Result> results = resultService.fetchuserResults(user);
    return ResponseEntity.ok(results);
  }

  @GetMapping("/getResults/category/{cid}")
  public ResponseEntity<List<Result>> getResuktsByCategory(
    @PathVariable int cid
  ) throws ResourceNotFoundException {
    Category category = modelMapper.map(
      categoryService.getCategoryById(cid),
      Category.class
    );
    List<Result> results = resultService.fetchResultsByCategory(category);
    return ResponseEntity.ok(results);
  }

  @DeleteMapping("/deleteResult/user/{uid}/category/{cid}")
  public ResponseEntity<String> deleteResult(
    @PathVariable int uid,
    @PathVariable int cid
  ) throws ResourceNotFoundException {
    User user = userService.getUserById(uid);
    Category category = modelMapper.map(
      categoryService.getCategoryById(cid),
      Category.class
    );
    resultService.deleteResult(new Resultpk(user, category));
    return ResponseEntity.ok("Deleted succesfully!!");
  }
}

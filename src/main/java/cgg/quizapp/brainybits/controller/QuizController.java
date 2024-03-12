package cgg.quizapp.brainybits.controller;

import cgg.quizapp.brainybits.dtos.QuizQuestionDTO;
import cgg.quizapp.brainybits.services.QuizService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class QuizController {

  @Autowired
  private QuizService quizService;

  @PostMapping("/addquestion")
  public ResponseEntity<QuizQuestionDTO> addQuestion(
    @RequestBody QuizQuestionDTO quizQuestion
  ) {
    QuizQuestionDTO question = quizService.addQuestion(quizQuestion);
    return ResponseEntity.ok(question);
  }

  @DeleteMapping("/deletequestion/{id}")
  public ResponseEntity<String> deleteQuestion(@PathVariable int id) {
    quizService.deleteQuestion(id);
    return ResponseEntity.ok("Deleted Succesfully");
  }

  @PutMapping("updatequestion/{id}")
  public ResponseEntity<QuizQuestionDTO> updateQuestion(
    @PathVariable int id,
    @RequestBody QuizQuestionDTO updated
  ) {
    QuizQuestionDTO updateQuestion = quizService.updateQuestion(id, updated);
    return ResponseEntity.ok(updateQuestion);
  }

  @GetMapping("getquestions/{cat_id}")
  public ResponseEntity<List<QuizQuestionDTO>> getMethodName(
    @PathVariable int cat_id
  ) {
    List<QuizQuestionDTO> questions = quizService.getQuestionsByCategory(
      cat_id
    );
    return ResponseEntity.ok(questions);
  }
}

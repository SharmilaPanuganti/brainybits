package cgg.quizapp.brainybits.controller;

import cgg.quizapp.brainybits.dtos.CategoryDTO;
import cgg.quizapp.brainybits.services.CategoryService;
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
public class CategoryController {

  @Autowired
  private CategoryService catService;

  @PostMapping("/addCategory")
  public ResponseEntity<CategoryDTO> addCategory(
    @RequestBody CategoryDTO category
  ) {
    System.out.println(category.getName());

    CategoryDTO saved = catService.addCategory(category);
    return ResponseEntity.ok(saved);
  }

  @GetMapping("/getCategories")
  public ResponseEntity<List<CategoryDTO>> getAllCategories() {
    List<CategoryDTO> categories = catService.getAllCategories();

    return ResponseEntity.ok(categories);
  }

  @PutMapping("/updateCategory/{id}")
  public ResponseEntity<CategoryDTO> updateCategory(
    @PathVariable int id,
    @RequestBody CategoryDTO category
  ) {
    CategoryDTO updateCategory = catService.updateCategory(id, category);
    return ResponseEntity.ok(updateCategory);
  }

  @DeleteMapping("deleteCategory/{id}")
  public ResponseEntity<String> deleteCategory(@PathVariable int id) {
    catService.deleteCategory(id);
    return ResponseEntity.ok("Deleted successfully");
  }
}

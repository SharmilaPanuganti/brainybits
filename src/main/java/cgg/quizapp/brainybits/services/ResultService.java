package cgg.quizapp.brainybits.services;

import cgg.quizapp.brainybits.entities.Category;
import cgg.quizapp.brainybits.entities.Result;
import cgg.quizapp.brainybits.entities.Resultpk;
import cgg.quizapp.brainybits.entities.User;
import cgg.quizapp.brainybits.exceptions.ResourceNotFoundException;
import java.util.List;

public interface ResultService {
  public Result addResult(Result result);

  public void deleteResult(Resultpk details) throws ResourceNotFoundException;

  public List<Result> getResults();

  public Result fetchResult(Resultpk details) throws ResourceNotFoundException;

  public List<Result> fetchuserResults(User user);

  public List<Result> fetchResultsByCategory(Category category);
}

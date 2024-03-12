package cgg.quizapp.brainybits.services.impl;

import cgg.quizapp.brainybits.entities.Category;
import cgg.quizapp.brainybits.entities.Result;
import cgg.quizapp.brainybits.entities.Resultpk;
import cgg.quizapp.brainybits.entities.User;
import cgg.quizapp.brainybits.exceptions.ResourceNotFoundException;
import cgg.quizapp.brainybits.repositories.ResultRepository;
import cgg.quizapp.brainybits.services.ResultService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultService {

  @Autowired
  private ResultRepository resultRepo;

  @Override
  public Result addResult(Result result) {
    return resultRepo.save(result);
  }

  @Override
  public void deleteResult(Resultpk details) throws ResourceNotFoundException {
    resultRepo
      .findById(details)
      .orElseThrow(() ->
        new ResourceNotFoundException("result", "id", details + "")
      );
    resultRepo.deleteById(details);
  }

  @Override
  public List<Result> getResults() {
    return resultRepo.findAll();
  }

  @Override
  public Result fetchResult(Resultpk details) throws ResourceNotFoundException {
    Result result = resultRepo
      .findById(details)
      .orElseThrow(() ->
        new ResourceNotFoundException("result", "id", details + "")
      );
    return result;
  }

  @Override
  public List<Result> fetchuserResults(User user) {
    List<Result> results = resultRepo.findByUser(user);
    return results;
  }

  @Override
  public List<Result> fetchResultsByCategory(Category category) {
    return resultRepo.findByCategory(category);
  }
}

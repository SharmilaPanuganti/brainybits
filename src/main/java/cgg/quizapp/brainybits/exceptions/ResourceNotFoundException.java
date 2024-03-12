package cgg.quizapp.brainybits.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends Exception {

  public String message;

  public ResourceNotFoundException(String resName, String field, String id) {
    message = "Resource Not Found :" + resName + " " + field + " of " + id;
  }
}

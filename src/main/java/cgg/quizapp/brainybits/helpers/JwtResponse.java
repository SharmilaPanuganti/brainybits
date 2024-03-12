package cgg.quizapp.brainybits.helpers;

import cgg.quizapp.brainybits.entities.User;
import lombok.Data;

@Data
public class JwtResponse {

  private String token;
  private User user;
}

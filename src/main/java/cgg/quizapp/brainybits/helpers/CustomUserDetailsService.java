package cgg.quizapp.brainybits.helpers;

import cgg.quizapp.brainybits.entities.User;
import cgg.quizapp.brainybits.exceptions.ResourceNotFoundException;
import cgg.quizapp.brainybits.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepo;

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    User user = null;
    try {
      user =
        userRepo
          .findByEmail(username)
          .orElseThrow(() ->
            new ResourceNotFoundException("user", "email", username + "")
          );
    } catch (ResourceNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return new CustomUserDetails(user);
  }
}

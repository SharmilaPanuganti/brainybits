package cgg.quizapp.brainybits.controller;

import cgg.quizapp.brainybits.entities.User;
import cgg.quizapp.brainybits.helpers.CustomUserDetailsService;
import cgg.quizapp.brainybits.helpers.JwtRequest;
import cgg.quizapp.brainybits.helpers.JwtResponse;
import cgg.quizapp.brainybits.security.JwtHelper;
import cgg.quizapp.brainybits.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private UserService userService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private CustomUserDetailsService customUserDetailService;

  @Autowired
  private JwtHelper jwtTokenHelper;

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/login")
  public ResponseEntity<JwtResponse> createToken(
    @RequestBody JwtRequest jwtRequest
  ) throws Exception {
    System.out.println(jwtRequest);

    try {
      this.authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
            jwtRequest.getUsername(),
            jwtRequest.getPassword()
          )
        );
    } catch (UsernameNotFoundException e) {
      e.printStackTrace();
      throw new Exception("Bad Credentials");
    } catch (BadCredentialsException e) {
      e.printStackTrace();
      throw new Exception("Bad Credentials");
    }
    UserDetails userDetails =
      this.customUserDetailService.loadUserByUsername(jwtRequest.getUsername());
    System.out.println(userDetails);
    String token = this.jwtTokenHelper.generateToken(userDetails);
    System.out.println("JWT" + token);
    JwtResponse response = new JwtResponse();
    //{"token":"value"}
    response.setToken(token);
    response.setUser(this.mapper.map(userDetails, User.class));

    return ResponseEntity.ok(response);
  }

  //register new user api
  @PostMapping("/register")
  public ResponseEntity<User> registerUser(@RequestBody User user) {
    try {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      User registeredUser = this.userService.addUser(user);

      return new ResponseEntity<User>(registeredUser, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<User>(user, HttpStatusCode.valueOf(409));
    }
  }
}

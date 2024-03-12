package cgg.quizapp.brainybits;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BrainybitsApplication {

  public static void main(String[] args) {
    SpringApplication.run(BrainybitsApplication.class, args);
    System.out.println("Started");
  }

  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }
}

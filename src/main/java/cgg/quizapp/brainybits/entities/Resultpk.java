package cgg.quizapp.brainybits.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resultpk implements Serializable {

  private User user;

  private Category category;
}

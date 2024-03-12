package cgg.quizapp.brainybits.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

  @Column(name = "user_name")
  private String userName;

  @Column(unique = true, nullable = false)
  private String email;

  private String password;

  @Id
  private Integer empId;

  private String profile;

  private String role;
}

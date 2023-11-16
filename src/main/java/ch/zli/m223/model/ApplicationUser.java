package ch.zli.m223.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@NamedQueries({
    @NamedQuery(name = "ApplicationUser.findByEmail", query = "SELECT u FROM ApplicationUser u WHERE u.email = :email")
})
public class ApplicationUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column
  private String FirstName;

  @Column
  private String LastName;

  @Column
  private String Role;

  @OneToMany(mappedBy = "applicationUser")
  @JsonIgnoreProperties("user")
  @Fetch(FetchMode.JOIN)
  private Set<Buchung> buchungen = new HashSet<>();

  public String getRole() {
    return Role;
  }

  public void setRole(String role) {
    Role = role;
  }

  public String getLastName() {
    return LastName;
  }

  public void setLastName(String lastName) {
    LastName = lastName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return FirstName;
  }

  public void setFirstName(String firstname) {
    this.FirstName = firstname;
  }

  public Set<Buchung> getBuchungen() {
    return buchungen;
  }

  public void setBuchungen(Set<Buchung> buchungen) {
    this.buchungen = buchungen;
  }

  public void addBuchung(Buchung buchung) {
    buchungen.add(buchung);
    buchung.setUser(this);
  }

  public void removeBuchung(Buchung buchung) {
    buchungen.remove(buchung);
    buchung.setUser(null);
  }
}

package ch.zli.m223.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
// import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Credential;
import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class LoginService {

  @Inject
  RegisterUserservice applicationUserService;

  public Response authenticate(Credential credential) {
    Optional<ApplicationUser> principal = applicationUserService.findByEmail(credential.getEmail());
    try {
      if (principal.isPresent() && principal.get().getPassword().equals(credential.getPassword())) {
        if (principal.get().getRole().equals("Mitglied")) {
          String token = Jwt
              .issuer("https://zli.example.com/")
              .upn(credential.getEmail())
              .groups(new HashSet<>(Arrays.asList("Mitglied")))
              .expiresIn(Duration.ofHours(24))
              .sign();
          return Response
              .ok(principal.get())
              .header("Authorization", "Bearer " + token)
              .entity(token)
              .build();
        }
        if (principal.get().getRole().equals("Admin")) {
          String token = Jwt
              .issuer("https://zli.example.com/")
              .upn(credential.getEmail())
              .groups(new HashSet<>(Arrays.asList("Admin")))
              .expiresIn(Duration.ofHours(24))
              .sign();
          return Response
              .ok(principal.get())
              .header("Authorization", "Bearer " + token)
              .entity(token)
              .build();
        }
      }
    } catch (Exception e) {
      System.err.println("Couldn't validate password.");
    }

    return Response.status(Response.Status.FORBIDDEN).build();
  }
}

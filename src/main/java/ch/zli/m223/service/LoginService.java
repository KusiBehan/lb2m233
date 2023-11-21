package ch.zli.m223.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
      if (principal.isPresent()
          && applicationUserService.checkPassword(credential.getPassword(), principal.get().getPassword())) {
        String role = principal.get().getRole();
        String token = generateToken(credential.getEmail(), role);

        if (isSessionValid(principal.get().getEmail(), token)) {
          updateSession(principal.get().getEmail(), token);
          return buildResponse(principal.get(), token);
        } else {
          return Response.status(Response.Status.FORBIDDEN)
              .entity("Session is still running")
              .build();
        }
      }
    } catch (Exception e) {
      System.err.println("Couldn't validate password.");
    }

    return Response.status(Response.Status.FORBIDDEN).build();
  }

  private String generateToken(String email, String role) {
    return Jwt.issuer("https://zli.example.com/")
        .upn(email)
        .groups(new HashSet<>(Arrays.asList(role)))
        .expiresIn(Duration.ofHours(24))
        .sign();
  }

  private boolean isSessionValid(String userEmail, String token) {
    if (applicationUserService.getSessionUser().equals(userEmail)) {
      if (applicationUserService.getSessionToken().equals(token) ||
          applicationUserService.getSessionToken().isEmpty()) {
        return true;
      } else {
        return false;
      }
    } else {
      return true;
    }
  }

  private void updateSession(String userEmail, String token) {
    applicationUserService.setSessionUser(userEmail);
    applicationUserService.setSessionToken(token);
  }

  private Response buildResponse(ApplicationUser principal, String token) {
    return Response.ok(principal)
        .header("Authorization", "Bearer " + token)
        .entity(token)
        .build();
  }
}

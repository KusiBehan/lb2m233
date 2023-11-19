package ch.zli.m223.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.zli.m223.model.ApplicationUser;

@ApplicationScoped
public class RegisterUserservice {
    @Inject
    EntityManager entityManager;

    @Transactional
    public ApplicationUser createUser(ApplicationUser user, String... Bearervalue) {
        if (Bearervalue.length > 0) {
            Optional<ApplicationUser> sessionUser = getSessionUser(Bearervalue[0]);
            if (sessionUser.get().getRole().equals("Admin")) {
                return entityManager.merge(user);
            }
        }

        if (findAll().isEmpty()) {
            user.setRole("Admin");
        } else {
            user.setRole("Mitglied");
        }
        return entityManager.merge(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        var entity = entityManager.find(ApplicationUser.class, id);
        entityManager.remove(entity);
    }

    @Transactional
    public ApplicationUser updateUser(Long id, ApplicationUser user) {
        user.setId(id);
        return entityManager.merge(user);
    }

    public List<ApplicationUser> findAll() {
        var query = entityManager.createQuery("FROM ApplicationUser", ApplicationUser.class);
        return query.getResultList();
    }

    public Optional<ApplicationUser> findByEmail(String email) {
        return entityManager
                .createNamedQuery("ApplicationUser.findByEmail", ApplicationUser.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }

    public static String extractUpnFromJwtClaims(String jwtClaims) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode claimsNode = objectMapper.readTree(jwtClaims);
            return claimsNode.get("upn").asText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Optional<ApplicationUser> getSessionUser(String BearerValue) {
        String JwtToken = BearerValue.replace("Bearer ", "");
        String[] chunks = JwtToken.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        String email = extractUpnFromJwtClaims(payload);
        return findByEmail(email);
    }
}
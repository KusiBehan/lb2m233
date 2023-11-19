package ch.zli.m223.controller;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
// import org.hibernate.mapping.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Buchung;
import ch.zli.m223.service.ApplicationUserService;
import ch.zli.m223.service.BuchungService;

@Path("/buchung")
@Tag(name = "buchung", description = "Handling of buchungen")
@RolesAllowed({ "Mitglied", "Admin" })
public class BuchungController {

    @Inject
    BuchungService buchungService;

    @Inject
    ApplicationUserService applicationUserService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new buchung.", description = "Creates a new buchung and returns thenewly added buchung.")
    public Buchung create(Buchung buchung, @HeaderParam("Authorization") String TokenValue) {
        String JwtToken = TokenValue.replace("Bearer ", "");
        String[] chunks = JwtToken.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payload = new String(decoder.decode(chunks[1]));

        String email = extractUpnFromJwtClaims(payload);
        Optional<ApplicationUser> sessionUser = applicationUserService.findByEmail(email);

        buchung.setUser(sessionUser.get());

        return buchungService.createBuchung(buchung);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all your Booking", description = "Get a list of your bookings")
    public List<Buchung> getBookings() {
        return buchungService.getall();
    }

    public static String extractUpnFromJwtClaims(String jwtClaims) {
        try {
            // Parse the JSON string into a JsonNode using Jackson ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode claimsNode = objectMapper.readTree(jwtClaims);

            // Extract the "upn" claim as a String
            return claimsNode.get("upn").asText();
        } catch (Exception e) {
            // Handle JSON parsing or other errors
            e.printStackTrace();
        }

        return null;
    }

}

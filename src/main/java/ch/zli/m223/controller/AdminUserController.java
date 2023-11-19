package ch.zli.m223.controller;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.service.RegisterUserservice;
import ch.zli.m223.service.BuchungService;

@Path("/user")
@Tag(name = "buchung", description = "Handling of buchungen")
@RolesAllowed({ "Admin" })
public class AdminUserController {

    @Inject
    BuchungService buchungService;

    @Inject
    public RegisterUserservice applicationUserService;

    // @POST
    // @Produces(MediaType.APPLICATION_JSON)
    // @Consumes(MediaType.APPLICATION_JSON)
    // @Operation(summary = "Creates a new buchung.", description = "Creates a new
    // buchung and returns thenewly added buchung.")
    // public Buchung create(Buchung buchung, @HeaderParam("Authorization") String
    // TokenValue) {
    // Optional<ApplicationUser> sessionUser = getSessionUser(TokenValue);
    // buchung.setUser(sessionUser.get());
    // return buchungService.createBuchung(buchung);
    // }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all Users", description = "Liste von Users")
    public List<ApplicationUser> getUsers(@HeaderParam("Authorization") String TokenValue) {
        return applicationUserService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create Users", description = "Users erstellen")
    public ApplicationUser createUser(@Valid @RequestBody ApplicationUser applicationUser,
            @HeaderParam("Authorization") String TokenValue) {
        return applicationUserService.createUser(applicationUser, TokenValue);
    }

    // @DELETE
    // @Path("{id}")>
    // @Produces(MediaType.APPLICATION_JSON)
    // @Operation(summary = "Buchungen stornieren", description = "Buchungen
    // stornieren")
    // public Buchung delete(@PathParam("id") Long buchungId) {
    // return buchungService.deleteBuchung(buchungId);
    // }

}

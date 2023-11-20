package ch.zli.m223.controller;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
@Tag(name = "user", description = "Handling of user")
@RolesAllowed({ "Admin" })
public class AdminUserController {

    @Inject
    BuchungService buchungService;

    @Inject
    public RegisterUserservice applicationUserService;

    @Path("/{id}")
    @PUT
    @Operation(summary = "Updates an user.", description = "Updates an user by its id.")
    public ApplicationUser update(@PathParam("id") Long id, ApplicationUser user) {
        return applicationUserService.updateUser(id, user);
    }

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

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "User löschen", description = "User löschen")
    public ApplicationUser delete(@PathParam("id") Long buchungId) {
        return applicationUserService.deleteUser(buchungId);
    }
}

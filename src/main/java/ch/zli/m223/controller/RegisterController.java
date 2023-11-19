package ch.zli.m223.controller;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.service.RegisterUserservice;

@Path("/register")
@Tag(name = "Users", description = "register of users")
public class RegisterController {

    @Inject
    RegisterUserservice userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "summary", description = "description")
    public ApplicationUser registerUser(@Valid @RequestBody ApplicationUser user) {
        return userService.createUser(user);
    }
}

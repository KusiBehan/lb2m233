package ch.zli.m223.controller;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.Buchung;
import ch.zli.m223.service.BuchungService;

@Path("/buchung")
@Tag(name = "buchung", description = "Handling of buchungen")
@RolesAllowed({ "User", "Admin" })
public class BuchungController {

    @Inject
    BuchungService buchungService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new buchung.", description = "Creates a new buchung and returns thenewly added buchung.")
    public Buchung create(Buchung buchung) {
        return buchungService.createBuchung(buchung);
    }
}

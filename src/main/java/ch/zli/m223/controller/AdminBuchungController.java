package ch.zli.m223.controller;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

import ch.zli.m223.model.Buchung;
import ch.zli.m223.service.BuchungService;

@Path("/booking/admin")
@Tag(name = "Admin Buchungen", description = "Handling of Admin Buchungen")
@RolesAllowed({ "Admin" })
public class AdminBuchungController {

    @Inject
    BuchungService buchungService;

    @Path("/{id}/accept")
    @PUT
    @Operation(summary = "accept booking", description = "Accept the pending bookings")
    public Buchung accept(@PathParam("id") Long id) {
        return buchungService.acceptBuchung(id);
    }

    @Path("/{id}/decline")
    @PUT
    @Operation(summary = "decline booking", description = "decline the pending bookings")
    public Buchung decline(@PathParam("id") Long id) {
        return buchungService.declineBuchung(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all Bookings", description = "Get a list of all bookings")
    public List<Buchung> getBookings() {
        return buchungService.getall();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new buchung.", description = "Creates a new buchung and returns the newly added buchung.")
    public Buchung create(Buchung buchung) {
        return buchungService.createBuchung(buchung);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Buchungen Löschen", description = "ausgewähle Buchung löschen")
    public Buchung delete(@PathParam("id") Long buchungId) {
        return buchungService.deleteBuchung(buchungId);
    }
}
